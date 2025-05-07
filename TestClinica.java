package clinicarehab;

public class TestClinica {
    public static void main(String[] args) {
        // 1. Probar conexión
        System.out.println("=== Probando conexión a MySQL ===");
        Conectate conexion = new Conectate();
        conexion.desconectar();
        
        // 2. Probar operaciones CRUD
        System.out.println("\n=== Probando operaciones con pacientes ===");
        Pagina1_2 dao = new Pagina1_2();
        
        // Insertar
        System.out.println("Agregando paciente...");
        dao.NuevoPaciente(
            "María", "Gómez", "López", "EXP-002", 
            "28", "2023-10-02", "09:30", "F", 
            "1995-03-20", "Avenida 456", "Urgente", 
            "Dr. Pérez", "Lic. Rodríguez", "Carlos"
        );
        
        // Consultar
        System.out.println("\nListado de pacientes:");
        Object[][] datos = dao.getDatos();
        for (Object[] fila : datos) {
            System.out.println(java.util.Arrays.toString(fila));
        }
    }
}
