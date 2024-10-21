public class ChainOfResponsibilityDP {
    public static void main(String[] args) {
        ApprovalChain approvalChain = new ApprovalChain();

        ExpenseRequest request1 = new ExpenseRequest(100, "Office Supplies");
        ExpenseRequest request2 = new ExpenseRequest(200, "Team Lunch");
        ExpenseRequest request3 = new ExpenseRequest(1000, "Conference Fees");
        ExpenseRequest request4 = new ExpenseRequest(2000, "New Equipment");

        System.out.println("Processing Request 1:");
        approvalChain.processRequest(request1);
        System.out.println("\nProcessing Request 2:");
        approvalChain.processRequest(request2);
        System.out.println("\nProcessing Request 3:");
        approvalChain.processRequest(request3);
        System.out.println("\nProcessing Request 4:");
        approvalChain.processRequest(request4);
    }
}

class ApprovalChain {
    private Approver firstApprover;

    public ApprovalChain() {
        TeamLead teamLead = new TeamLead();
        Manager manager = new Manager();
        Director director = new Director();
        CEO ceo = new CEO();

        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);
        director.setNextApprover(ceo);

        this.firstApprover = teamLead;
    }

    public void processRequest(ExpenseRequest request) {
        firstApprover.approveExpense(request);
    }
}

class ExpenseRequest {
    private double amount;
    private String purpose;

    public ExpenseRequest(double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurpose() {
        return purpose;
    }
}

abstract class Approver {
    protected Approver nextApprover;

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    public abstract void approveExpense(ExpenseRequest request);
}

class CEO extends Approver {

    @Override
    public void approveExpense(ExpenseRequest request) {
        if (request.getAmount() > 5000) {
            System.out.println("CEO approved the expense for " + request.getPurpose());
        } else if (nextApprover != null) {
            nextApprover.approveExpense(request);
        }
    }
}

class Director extends Approver {

    @Override
    public void approveExpense(ExpenseRequest request) {
        if (request.getAmount() <= 5000) {
            System.out.println("Director approved the expense for " + request.getPurpose());
        } else if (nextApprover != null) {
            nextApprover.approveExpense(request);
        }
    }
}

class TeamLead extends Approver {

    @Override
    public void approveExpense(ExpenseRequest request) {
        if (request.getAmount() <= 500) {
            System.out.println("TeamLead approved the expense for " + request.getPurpose());
        } else if (nextApprover != null) {
            nextApprover.approveExpense(request);
        }
    }
}

class Manager extends Approver {

    @Override
    public void approveExpense(ExpenseRequest request) {
        if (request.getAmount() <= 1000) {
            System.out.println("Manager approved the expense for " + request.getPurpose());
        } else if (nextApprover != null) {
            nextApprover.approveExpense(request);
        }
    }
}

