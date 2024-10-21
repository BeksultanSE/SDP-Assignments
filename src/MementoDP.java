import java.util.ArrayList;
import java.util.List;

public class MementoDP {
    public static void main(String[] args) {
        Document document = new Document();
        VersionControl versionControl = new VersionControl();

        document.setContent("Version 1: Initial content.");
        versionControl.saveVersion(document.saveVersion());

        document.setContent("Version 2: Updated content with some changes.");
        versionControl.saveVersion(document.saveVersion());

        document.setContent("Version 3: Further changes made to the content.");
        versionControl.saveVersion(document.saveVersion());

        System.out.println("Saved Document Versions:");
        versionControl.listVersions();

        System.out.println("\nRestoring to Version 1...");
        document.restoreVersion(versionControl.getVersion(0));
        System.out.println("Current Document Content: " + document.getContent());

        System.out.println("\nRestoring to Version 2...");
        document.restoreVersion(versionControl.getVersion(1));
        System.out.println("Current Document Content: " + document.getContent());
    }
}


class Document {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public DocumentVersion saveVersion() {
        return new DocumentVersion(content);
    }

    public void restoreVersion(DocumentVersion memento) {
        this.content = memento.getContent();
    }
}

class DocumentVersion {
    private final String content;

    public DocumentVersion(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class VersionControl {
    private List<DocumentVersion> versions = new ArrayList<>();

    public void saveVersion(DocumentVersion version) {
        versions.add(version);
        System.out.println("Version saved.");
    }

    public void listVersions() {
        for (int i = 0; i < versions.size(); i++) {
            System.out.println("Version " + (i + 1) + ": " + versions.get(i).getContent());
        }
    }

    public DocumentVersion getVersion(int index) {
        if (index >= 0 && index < versions.size()) {
            return versions.get(index);
        }
        try {
            throw new IllegalArgumentException("Invalid version index");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
