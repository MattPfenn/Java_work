package ch.hearc;

import java.io.PrintStream;
import java.util.Scanner;

public class AgendaPerson {

	private String name;
	private String address;
	private String city;
	private String postalCode;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param name
	 * @param address
	 * @param city
	 * @param postalCode
	 * @param phone
	 */
	public AgendaPerson(String name, String address, String city,
			String postalCode, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
	}

	public void write(PrintStream out) {
		out.println(name);
		out.println(address);
		out.println(city);
		out.println(postalCode);
		out.println(phone);
	}

	@Override
	public String toString() {
		return "AgendaPerson [name=" + name + ", address=" + address
				+ ", city=" + city + ", postalCode=" + postalCode + ", phone="
				+ phone + "]";
	}
	public static AgendaPerson scan(Scanner scan) {
		String name = scan.nextLine();
		String address = scan.nextLine();
		String city = scan.nextLine();
		String postalCode = scan.nextLine();
		String phone = scan.nextLine();
		return new AgendaPerson(name, address, city, postalCode, phone);
	}

}
