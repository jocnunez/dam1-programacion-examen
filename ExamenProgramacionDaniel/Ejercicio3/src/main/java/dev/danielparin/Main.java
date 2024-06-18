package dev.danielparin;
//Necesario hacer un proyecto maven para importar las dependencias (pom.xml).

import dev.danielparin.models.Product;

import java.sql.*;

class Main {



    private Connection connection;
    //Haré el ejercicio con SQLite, por lo que pongo su conexión
    private final String databasePath = "jdbc:sqlite:products.db";


    public Main() {

        try {
            connection = DriverManager.getConnection(databasePath); // Nos conectamos a la base de datos.
            createTable(); // Creamos la tabla.
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }



    private void createTable() {

        // Ponemos la sentencia para crear la tabla si no existe.

        String sql = """
                CREATE TABLE IF NOT EXISTS Product(
                  id TEXT PRIMARY KEY,
                  nombre TEXT,
                  precio REAL
                );
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql); // Ejecutamos la sentencia pasándole el string de sql.
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    public void insertProduct(String nombre, Double precio) {
        //Instanciamos el objeto para que se genere la uuid, también lo podríamos haber puesto aquí directamente y que la uuid esté solo en la bbdd
        Product product = new Product(nombre, precio);

        String sql = "INSERT INTO Product(id, nombre, precio) VALUES(?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate(); // Ejecutamos los cambios.

        } catch (SQLException e) {
            System.err.println("Error al insertar un producto: " + e.getMessage());
        }
    }

    public void actualizarProducto(String id,String nombre, Double precio) {

        // Ponemos la sentencia para actualizar un coche.
        String sql = "UPDATE Product SET nombre=?, precio=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            //Aquí no instanciamos nada al no necesitar un id nuevo
            preparedStatement.setString(1, nombre);
            preparedStatement.setDouble(2, precio);
            preparedStatement.setString(3, id);

            // Vamos a comprobar si se ha actualizado bien.

            int filasAfectadas = preparedStatement.executeUpdate(); // Nos devolverá las filas afectadas por nuestra sentencia.

            if (filasAfectadas > 0) {
                // Si se han afectado más de 0 filas es que la consulta se ha realizado bien.
                System.out.println("Se ha actualizado correctamente.");
            } else {
                // Por el contrario si no se afecta nada, es porque no había ningún producto con la id introducida.
                System.err.println("No se encontró ningún producto con ese id.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    public void eliminarProducto(String id) {

        String sql = "DELETE FROM Product WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Se ha eliminado el producto correctamente.");
            } else {
                System.err.println("No se ha encontrado ningún producto con esa id.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    public void mostrarProductos() {

        String sql = "SELECT * FROM Product";

        // Mostramos todos los productos, para hacer que sea solo uno -> "SELECT * FROM Product WHERE id=?"

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Mientras que haya resultados, los imprimimos.
            while (resultSet.next()) {
                System.out.println("*****************************************************");
                System.out.println("ID: " + resultSet.getString("id"));
                System.out.println("Nombre: " + resultSet.getString("nombre"));
                System.out.println("Precio: " + resultSet.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar los coches: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        //Instanciamos el main para la conexión
        Main main = new Main();

        //Insertamos productos.
        main.insertProduct("Movil",123.4);
        main.insertProduct("Portatil", 450.0);
        main.insertProduct("Reloj",12.35);

        //Leemos.
        main.mostrarProductos();

        //Actualizamos uno
        main.actualizarProducto("f083afdf-6131-43d5-ace0-52818d0511a4","Reloj",8.5);

        //Leemos
        main.mostrarProductos();

        //Eliminamos uno
        main.eliminarProducto("f083afdf-6131-43d5-ace0-52818d0511a4");

        main.mostrarProductos();
    }
}
/*
    He usado OOP a la hora de pasar datos a las sentencias sql.

    En primer lugar, he dado la responsabilidad al constructor de generar la uuid directamente al instanciar un objeto, por eso
    solo pasamos dos valores en los insertProduct porque el ID se genera automático.
    Aparte a la hora de dar valores a las sentencias sql se producía un problema, ya que una uuid no es un String, por tanto, me daba
    error al hacer setString y getString por eso he añadido en el getter específico de la uuid que lo pase a String.
 */