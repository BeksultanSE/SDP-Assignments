public class TemplateDP {
    public static void main(String[] args) {
        ReportingSystem reportingSystem = new ReportingSystem();

        System.out.println("Generating HTML Report:");
        ReportGenerator htmlReport = new HTMLReportGenerator();
        reportingSystem.generateReport(htmlReport);

        System.out.println("Generating PDF Report:");
        ReportGenerator pdfReport = new PDFReportGenerator();
        reportingSystem.generateReport(pdfReport);

        System.out.println("Generating Plain Text Report:");
        ReportGenerator plainTextReport = new PlainTextReportGenerator();
        reportingSystem.generateReport(plainTextReport);
    }
}

abstract class ReportGenerator {

    public final void generateReport() {
        formatHeader();
        formatBody();
        formatFooter();
        System.out.println("Report generation complete.\n");
    }

    protected abstract void formatHeader();
    protected abstract void formatBody();
    protected abstract void formatFooter();
}

class HTMLReportGenerator extends ReportGenerator {
    @Override
    protected void formatHeader() {
        System.out.println("Formatting HTML Header...");
    }

    @Override
    protected void formatBody() {
        System.out.println("Formatting HTML Body...");
    }

    @Override
    protected void formatFooter() {
        System.out.println("Formatting HTML Footer...");
    }
}

class PDFReportGenerator extends ReportGenerator {
    @Override
    protected void formatHeader() {
        System.out.println("Formatting PDF Header...");
    }

    @Override
    protected void formatBody() {
        System.out.println("Formatting PDF Body...");
    }

    @Override
    protected void formatFooter() {
        System.out.println("Formatting PDF Footer...");
    }
}

class PlainTextReportGenerator extends ReportGenerator {
    @Override
    protected void formatHeader() {
        System.out.println("Formatting Plain Text Header...");
    }

    @Override
    protected void formatBody() {
        System.out.println("Formatting Plain Text Body...");
    }

    @Override
    protected void formatFooter() {
        System.out.println("Formatting Plain Text Footer...");
    }
}

class ReportingSystem {
    public void generateReport(ReportGenerator reportGenerator) {
        reportGenerator.generateReport();
    }
}

