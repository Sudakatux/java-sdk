package com.xapo.credit;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.xapo.exception.XapoException;
import com.xapo.model.PaymentRequest;
import com.xapo.model.XapoResponse;

public interface MicroPaymentService {

	public XapoResponse pay(PaymentRequest pr) throws XapoException, ClientProtocolException, IOException;

}
