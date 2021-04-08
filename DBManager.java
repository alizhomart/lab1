package home.alizhan.alizh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class DBManager {
    private Connection connection;

    public void connect(){
        try{
            //Подтягиваем драйвер
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Создаем подключение.
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test?useUnicode=true&serverTimezone=UTC","root", ""
            );
            System.out.println("CONNECTED!!!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            //Готовим запрос в базу данных
            PreparedStatement st = connection.prepareStatement("SELECT * FROM test");
            //При запуске данного запроса в ответ получаем объект класса ResultSet.
            ResultSet rs = st.executeQuery();
            //ResultSet - это наша таблица. Через цикл мы пробежимся по каждой строке данной таблицы
            while (rs.next()){
                //Получаем данные по каждой колонке. Конвертируем их в привычные нам переменные
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                //Добавляем новую user в список
                users.add(new User(id,name,surname, username, password));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(User u){
        try{
            PreparedStatement pr = connection.prepareStatement("INSERT INTO test(id, name, surname, username, password) values (null, ?,?,?,?)");
            pr.setString(1, u.getName());
            pr.setString(2, u.getSurname());
            pr.setString(3, u.getUsername());
            pr.setString(4, u.getPassword());

            pr.executeUpdate();
            pr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(User u){
        try{
            PreparedStatement pr = connection.prepareStatement("update test set name=?, surname=?, username=?, password=? where id=?");

            pr.setString(1, u.getName());
            pr.setString(2, u.getSurname());
            pr.setString(3, u.getUsername());
            pr.setString(4, u.getPassword());
            pr.setLong(5, u.getId());

            pr.executeUpdate();
            pr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Long id){
        try{
            PreparedStatement pr =connection.prepareStatement("delete from test where id=?");
            pr.setLong(1, id);

            pr.executeUpdate();
            pr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}