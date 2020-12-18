

import java.sql.*;
import java.util.Scanner;

public class ConexionBD {
	
	static Connection connection = null;
	static Statement statement = null;
	

	public static void main(String[] args) {
		
		
		Connection connection = null;
		try {
			connection = nuevaConexion();
		} catch (SQLException e) {
			System.out.println("Error al conectar");
			e.printStackTrace();
		}
		if (connection != null) {
			Statement statement = null;
			try {
				statement = connection.createStatement();

			} catch (SQLException e) {
				System.out.println("La conexion esta cerrada");
				e.printStackTrace();
			}

			try {
				ResultSet resultSet = statement.executeQuery("select * from empleado");

				while (resultSet.next()) {
					String codigoEmpleado = resultSet.getString("codigo_empleado");
					String nombre = resultSet.getString("nombre");
				}
				Menu();
				connection.close();
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error con los datos");
			}
			

		}

	}
	
	public static  void Menu() {
		
		System.out.println("¿Que accion desea?");
		System.out.println("1-Añadir un cliente");
		System.out.println("2-Ver un cliente");
		System.out.println("3-Ver todos los clientes");
		System.out.println("4-Buscar un cliente");
		System.out.println("5-Editar un producto");
		System.out.println("0-Salir del programa");
		
		
		
		int opcion = 1;
		Scanner sc = new Scanner(System.in);
		while (opcion != 0) {
			try {
				opcion = sc.nextInt();
				switch (opcion) {
				case 1:addClient();break;
				case 2:showClient();break;
				case 3:showAllClients();break;
				case 4:searchClient();break;
				case 5:editProduct();break;
				case 0:System.exit(0);

				default:break;
				}
				
			} catch (Exception e) {
				System.out.println("Error");
			}
		}

	}

	static Connection nuevaConexion() throws SQLException {
		Connection conexion;
		conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "4DM1n4DM1n");
		return conexion;

	}

	
	
	public static void addClient() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca los datos del cliente");
		
		Cliente cliente = new Cliente();
		try {
			
			System.out.println("Introduzca el ID del cliente");
			cliente.setId_cliente(sc.nextInt());
			
			
			System.out.println("Introduzca el nombre del cliente");
			cliente.setNombre(sc.nextLine());
			
			System.out.println("Introduzca el nombre de contacto");
			cliente.setNombre_contacto(sc.nextLine());
			
			System.out.println("Introduzca el apellido de contacto");
			cliente.setApellido_contacto(sc.nextLine());
			
			System.out.println("Introduzca el telefono");
			cliente.setTelefono(sc.nextLine());
			
			System.out.println("Introduzca el numero de fax");
			cliente.setFax(sc.nextLine());
			
			System.out.println("Introduzca el la dirección 1");
			cliente.setLinea_direccion1(sc.nextLine());
			
			System.out.println("Introduzca el la dirección 2");
			cliente.setLinea_direccion2(sc.nextLine());
			
			System.out.println("Introduzca la ciudad");
			cliente.setCiudad(sc.nextLine());
			
			System.out.println("Introduzca la región");
			cliente.setRegion(sc.nextLine());
			
			System.out.println("Introduzca el país");
			cliente.setPais(sc.nextLine());
			
			System.out.println("Introduzca el código postal");
			cliente.setCodigo_postal(sc.nextLine());
			
			System.out.println("Introduzca el código de empleado representante de ventas");
			cliente.setCodigo_empleado_rep_ventas(sc.nextInt());
			
			System.out.println("Introduzca el límite de crédito");
			cliente.setLimite_credito(sc.nextDouble());
			
			System.out.println(cliente.toString() + "se ha guardado");
			
			String sentenciaSql = "INSERT INTO cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, linea_direccion1, linea_direccion2, ciudad, region, pais, codigo_postal, codigo_empleado_rep_ventas, limite_credito) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement sentenciaPreparada = null;
			
			try {
				
			sentenciaPreparada = connection.prepareStatement(sentenciaSql);
			
			sentenciaPreparada.setInt(1, cliente.getId_cliente());
			sentenciaPreparada.setString(2, cliente.getNombre());
			sentenciaPreparada.setString(3, cliente.getNombre_contacto());
			sentenciaPreparada.setString(4, cliente.getApellido_contacto());
			sentenciaPreparada.setString(5, cliente.getTelefono());
			sentenciaPreparada.setString(6, cliente.getFax());
			sentenciaPreparada.setString(7, cliente.getLinea_direccion1());
			sentenciaPreparada.setString(8, cliente.getLinea_direccion2());
			sentenciaPreparada.setString(9, cliente.getCiudad());
			sentenciaPreparada.setString(10, cliente.getRegion());
			sentenciaPreparada.setString(11, cliente.getPais());
			sentenciaPreparada.setString(12, cliente.getCodigo_postal());
			sentenciaPreparada.setInt(13, cliente.getCodigo_empleado_rep_ventas());
			sentenciaPreparada.setDouble(14, cliente.getLimite_credito());
			
			sentenciaPreparada.executeUpdate();
			System.out.println("ERROR");
			
			} catch (SQLException sqle) {
				
			sqle.printStackTrace();
			
			} finally {
				
			if (sentenciaPreparada != null)
				
				try {
					
					sentenciaPreparada.close();
					
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

	} catch (Exception e) {
		System.out.println("Has introducido un valor erroneo,volviendo al menu");
	}

}
				
			
			
	public static void showClient() {
		
		Scanner sc = new Scanner(System.in);
		int id_cliente;
		System.out.println("Introduzca el ID del cliente");
		id_cliente = sc.nextInt();
		sc.hasNextLine();
		
		String sentenciaSql = "SELECT * FROM cliente WHERE codigo_cliente = ?";
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultado = null;
		
		try {
			
			sentenciaPreparada = connection.prepareStatement(sentenciaSql);
			
			sentenciaPreparada.setInt(1, id_cliente);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		try {
			
			resultado = sentenciaPreparada.executeQuery();
			
			while (resultado.next()) {
				printCliente(resultado);
			}
		} catch (Exception sqle) {
			
			sqle.printStackTrace();
		} finally {
			if (sentenciaPreparada != null)
				try {
					sentenciaPreparada.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

	}

			
		
		
	
	
	public static void showAllClients() {
		
		String sentenciaSql = "SELECT * FROM cliente";
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultado = null;
		
		try {
			sentenciaPreparada = connection.prepareStatement(sentenciaSql);
			resultado = sentenciaPreparada.executeQuery();
			
			while (resultado.next()) {
				printCliente(resultado);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentenciaPreparada != null)
				try {
					sentenciaPreparada.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

	}
		
		
	
	
	public static void searchClient() {
		
		Scanner sc = new Scanner(System.in);
		String nombre_cliente;
		
		nombre_cliente = sc.nextLine();
		
		String sentenciaSql = "SELECT * FROM cliente WHERE nombre_cliente LIKE '%"+nombre_cliente+"%'";
		PreparedStatement sentenciaPreparada = null;
		ResultSet resultado = null;

		try {
			sentenciaPreparada = connection.prepareStatement(sentenciaSql);
			resultado = sentenciaPreparada.executeQuery();

			while (resultado.next()) {
				printCliente(resultado);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentenciaPreparada != null)
				try {
					sentenciaPreparada.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

	}
	
	public static void editProduct() {
		
		Scanner sc = new Scanner(System.in);
		Producto producto = new Producto();
		
		System.out.println("Introduzca el código del producto.");
		producto.setId_producto(sc.nextLine());
		System.out.println("Introduce los nuevos datos del producto. \n Introduce el nombre");
		producto.setNombre(sc.nextLine());
		System.out.println("Introduce la gama");
		producto.setGama(sc.nextLine());
		System.out.println("Introduce las dimensiones");
		producto.setDimensiones(sc.nextLine());
		System.out.println("Introduce el proveedor");
		producto.setProveedor(sc.nextLine());
		System.out.println("Introduce la descripción");
		producto.setDescripcion(sc.nextLine());
		System.out.println("Introduce la cantidad en stock");
		producto.setCantidad_en_stock(sc.nextLine());
		System.out.println("Introduce el precio de venta");
		producto.setPrecio_venta(sc.nextDouble());
		System.out.println("Introduce el precio del proveedor");
		producto.setPrecio_proveedor(sc.nextDouble());
		
		

		String sentenciaSql = "UPDATE producto SET nombre = ?, gama = ?, dimensiones = ?, proveedor = ?, descripcion = ?, cantidad_en_stock = ?, precio_venta = ?, precio_proveedor = ?" + "WHERE nombre = ?";
		PreparedStatement sentenciaPreparada = null;

		try {
			sentenciaPreparada = connection.prepareStatement(sentenciaSql);
			
			sentenciaPreparada.setString(1, producto.getNombre());
			sentenciaPreparada.setString(2, producto.getGama());
			sentenciaPreparada.setString(3, producto.getDimensiones());
			sentenciaPreparada.setString(4, producto.getProveedor());
			sentenciaPreparada.setString(5, producto.getDescripcion());
			sentenciaPreparada.setString(6, producto.getCantidad_en_stock());
			sentenciaPreparada.setDouble(7, producto.getPrecio_venta());
			sentenciaPreparada.setDouble(8, producto.getPrecio_proveedor());
			sentenciaPreparada.setString(9, producto.getId_producto());
			
			sentenciaPreparada.executeUpdate();
			
		} catch (Exception sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentenciaPreparada != null)
				try {
					sentenciaPreparada.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

	}
	private static void printCliente(ResultSet resultado) throws SQLException {
		
		System.out.println("ID de cliente: " + resultado.getInt(1));
		System.out.println("Nombre de cliente: " + resultado.getString(2));
		System.out.println("Nombre de contacto: " + resultado.getString(3));
		System.out.println("Apellido de contacto: " + resultado.getString(4));
		System.out.println("Teléfono: " + resultado.getString(5));
		System.out.println("Fax: " + resultado.getString(6));
		System.out.println("Dirección 1: " + resultado.getString(7));
		System.out.println("Dirección: " + resultado.getString(8));
		System.out.println("Ciudad: " + resultado.getString(9));
		System.out.println("Región: " + resultado.getString(10));
		System.out.println("País: " + resultado.getString(11));
		System.out.println("ID postal: " + resultado.getString(12));
		System.out.println("ID de empleado representante de ventas: " + resultado.getInt(13));
		System.out.println("Límite de crédito: " + resultado.getFloat(14));
		
	}

}



