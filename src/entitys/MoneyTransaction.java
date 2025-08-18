package entitys;

import java.time.LocalDateTime;

public class MoneyTransaction {
    private int idTransaction;
    private LocalDateTime dateTransaction;
    private boolean isMoneyIncome;
    private double amount;
    private String description;
    private Event event;
}
