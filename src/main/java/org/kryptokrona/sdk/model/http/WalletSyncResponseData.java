package org.kryptokrona.sdk.model.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WalletSyncResponseData {

	private List<String> items;

	private String status;
}
