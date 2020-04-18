package exchangeOfficePAO.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AuditService {
    private static AuditService auditService = null;
    private static File auditFile = null;

    private AuditService(){
        auditFile = new File("./Files/audit.csv");
    }

    public static AuditService getInstance(){
        if(auditService == null)
          auditService = new AuditService();
        return auditService;
    }

    public void writeAction(String actionName, long actionTimestamp) throws IOException {
        FileWriter fileWriter = new FileWriter(this.auditFile, true);
        fileWriter.append(actionName);
        fileWriter.append(',');
        fileWriter.append(String.valueOf(actionTimestamp));
        fileWriter.append('\n');
        fileWriter.close();
    }
}
