package teamfit;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseCart implements Comparable<ExerciseCart>, Serializable {
	private String cartId;
	private String cartCode;

	public ExerciseCart() {
		this(null, null);
	}

	public ExerciseCart(String cartId, String cartCode) {
		super();
		this.cartId = cartId;
		this.cartCode = cartCode;
	}

	public String getCartCode() {
		return cartCode;
	}

	public void setCartCode(String cartCode) {
		this.cartCode = cartCode;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	@Override
	public int compareTo(ExerciseCart o) {
		return this.cartId.compareToIgnoreCase(o.cartId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId);
	}

	@Override
	public boolean equals(Object obj) {
		ExerciseCart exerciseCart = null;
		if (obj instanceof ExerciseCart) {
			exerciseCart = (ExerciseCart) obj;
			return this.cartId.equals(exerciseCart.cartId);
		}
		return false;
	}

	@Override
	public String toString() {
		return "ExerciseCart [cartCode=" + cartCode + ", cartId=" + cartId + "]";
	}
}
