package com.melonltd.naber.endpoint.util;

public class Base64Exception extends RuntimeException {
	private static final long serialVersionUID = -551996051505932016L;

	public Base64Exception() {
	}

	public Base64Exception(String s) {
		super(s);
	}

	public Base64Exception(Throwable throwable) {
		super(throwable);
	}

	public Base64Exception(String s, Throwable throwable) {
		super(s, throwable);
	}
}
