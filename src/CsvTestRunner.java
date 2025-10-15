import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CsvTestRunner {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\Admin\\Documents\\KiemThu\\src\\testcase_kiem thu dong dieu khien.csv";
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

        int passCount = 0;
        int failCount = 0;

        for (int i = 1; i < lines.size(); i++) { // bỏ qua header
            String[] parts = lines.get(i).split(",");
            if (parts.length < 3) {
                System.out.println(i + "   ⚠️  Bỏ qua dòng không hợp lệ: " + lines.get(i));
                continue;
            }

            int nights = Integer.parseInt(parts[0].trim());
            int guests = Integer.parseInt(parts[1].trim());
            String expected = parts[2].trim();

            try {
                long actual = ResortCalculator.calculateTotalCost(nights, guests);
                String actualStr = String.valueOf(actual);

                if (actualStr.equals(expected)) {
                    passCount++;
                    System.out.println(i + "   PASS ✅ (expected=" + expected + ")");
                } else {
                    failCount++;
                    System.out.println(i + "   FAIL ❌ (expected=" + expected + ", actual=" + actualStr + ")");
                }

            } catch (Exception e) {
                String actualError = e.getMessage().trim();
                String expectedError = expected.replace("ERROR:", "").trim();

                if (actualError.equals(expectedError)) {
                    passCount++;
                    System.out.println(i + "   PASS ✅ (exception matches expected)");
                } else {
                    failCount++;
                    System.out.println(i + "   FAIL ❌ (expected=\"ERROR: " + expectedError +
                            "\", actual exception=\"" + actualError + "\")");
                }
            }
        }

        System.out.println("\n===== KẾT QUẢ TỔNG HỢP =====");
        System.out.println("Tổng số ca kiểm thử: " + (lines.size() - 1));
        System.out.println("PASS ✅: " + passCount);
        System.out.println("FAIL ❌: " + failCount);
    }
}
