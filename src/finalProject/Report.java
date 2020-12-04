//  Darren Jackson, Chintan Rami, Louis Slavotinek, Raymond Zegles
//  Report.java
//  Generates reports from database records.
//  Eclipse, Visual Studio Code

package finalProject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Report
{
    final private static String[] rentalColumns = {
        "Customer",
        "Make",
        "Model"
    };

    private static String[] buildFormatStrings(String[] columns, int[] columnWidths) {
        int columnCount = columns.length;
        String[] formatStrings = new String[columnCount];
        for (int col = 0; col < columnCount; ++col) {
            formatStrings[col] = "%-" + columnWidths[col] + "." + columnWidths[col] + "s";
        }
        return formatStrings;
    }

    private static String buildLine(String[] formatStrings, String[] columns) {
        String line = "";
        Queue<String> formatted = new LinkedList<String>();
        int columnCount = Math.min(formatStrings.length, columns.length);

        for (int col = 0; col < columnCount; ++col) {
            String column = String.format(
                formatStrings[col],
                columns[col]
            );

            formatted.add(column);
        }

        while (!formatted.isEmpty()) {
            String column = formatted.remove();
            if (!line.isEmpty()) {
                line += "\t";
            }
            line += column;
        }

        return line;
    }

    private static int[] calculateColumnWidths(
        String[] columns,
        ArrayList<List<String>> rows
    ) {
        int columnCount = columns.length;
        int[] columnWidths = new int[columnCount];
        for (int col = 0; col < columnCount; ++col) {
            columnWidths[col] = 0;

            for (List<String> row : rows) {
                if (row.size() <= columnCount) {
                    columnWidths[col] = Math.max(columnWidths[col], row.get(col).length());
                }
            }
        }
        return columnWidths;
    }

    public static List<String> generateRentalReport(Rental[] rentals) {
        int[] columnWidths = null;
        String[] formatStrings = null;
        ArrayList<String> lines = null;
        ArrayList<List<String>> rows = new ArrayList<List<String>>();

        //  Build rows
        for (Rental rental : rentals) {
            rows.add(getRentalRows(rental));
        }

        //  Calculate column widths
        columnWidths = calculateColumnWidths(rentalColumns, rows);

        //  Build column format strings
        formatStrings = buildFormatStrings(rentalColumns, columnWidths);

        //  Build actual lines of the report
        lines = new ArrayList<String>();
        lines.add(buildLine(formatStrings, rentalColumns));
        for (List<String> row : rows) {
            lines.add(buildLine(formatStrings, row.toArray(new String[0])));
        }

        return lines;
    }

    private static List<String> getRentalRows(Rental rental) {
        return List.of(
            rental.getCustomer().getName(),
            rental.getVehicle().getMake(),
            rental.getVehicle().getModel()
        );
    }
}
