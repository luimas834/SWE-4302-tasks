public class HTMLGenerator implements GenerateReport {
    @Override
    public void generateReport(Printreport pr,SaveFile sv) {
        System.out.println("Generating HTML report...");
        pr.printReport();
        sv.save();
    }
}
