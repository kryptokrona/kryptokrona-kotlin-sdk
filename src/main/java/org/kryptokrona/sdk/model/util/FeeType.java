package org.kryptokrona.sdk.model.util;

import lombok.Getter;
import lombok.Setter;

import static org.kryptokrona.sdk.config.Config.MINIMUM_FEE_PER_BYTE;

@Getter
@Setter
public class FeeType {
	
	private boolean isFixedFee = false;
	
	private boolean isFeePerByte = false;
	
	private double fixedFee = 0.0;
	
	private double feePerByte = 0.0;

	public static FeeType minimumFee() {
		return FeeType.feePerByte(MINIMUM_FEE_PER_BYTE);
	}
	
	public static FeeType feePerByte(double feePerByte) {
		FeeType fee = new FeeType();

		fee.isFeePerByte = true;
		fee.feePerByte = feePerByte;
		
		return fee;
	}
	
	public static FeeType fixedFee(double fixedFee) {
		FeeType fee = new FeeType();

		fee.isFixedFee = true;
		fee.fixedFee = fixedFee;
		
		return fee;
	}
}
