package businesslogic;

import com.autobizlogic.abl.annotations.Formula;
import com.autobizlogic.abl.annotations.ParentCopy;

public class LineItemLogic {

	@Formula("productPrice * qtyOrdered")
	public void deriveAmount() { }

	@ParentCopy("product.price")
	public void deriveProductPrice() { }
}
