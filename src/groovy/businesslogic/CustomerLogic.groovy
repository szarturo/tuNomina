package businesslogic

import abltutorial.Customer
import com.autobizlogic.abl.annotations.*

public class CustomerLogic {

    @Sum("purchaseOrders.amountTotal")
    public void deriveBalance() { }
}