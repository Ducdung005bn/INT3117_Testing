import java.nio.file.*;
import java.util.*;

public class CsvTestRunner {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Admin\\Documents\\KiemThu\\src\\testcase_kiem thu dong dieu khien.csv"));

        for (int i = 1; i < lines.size(); i++) { // bỏ qua header
            String[] parts = lines.get(i).split(",");
            int nights = Integer.parseInt(parts[0].trim());
            int guests = Integer.parseInt(parts[1].trim());

            try {
                long result = ResortCalculator.calculateTotalCost(nights, guests);
                System.out.println(i + "   " + result);
            } catch (Exception e) {
                System.out.println(i + "   ERROR: " + e.getMessage());
            }
        }
    }
}
