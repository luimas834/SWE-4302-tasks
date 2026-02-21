public class PdfGenerator implements GenerateReport {

    @Override
    public void generateReport(Printreport pr,SaveFile sv) {
        System.out.println("Generating PDF report...");
        pr.printReport();
        sv.save();
    }
}
