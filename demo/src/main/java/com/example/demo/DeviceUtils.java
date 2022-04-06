package com.example.demo;

import com.example.demo.model.ClientInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceUtils {

	/**
	 *
	 * @return
	 */
	public static ClientInfo getLocalInfo() {
		ClientInfo result = new ClientInfo();
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			try (final DatagramSocket socket = new DatagramSocket()) {
				socket.connect(InetAddress.getByName("223.5.5.5"), 10002);
				ip = socket.getLocalAddress().getHostAddress();
				socket.close();
			}
			//得到自己的對外ip
//			/*example
//			URL whatismyip = new URL("http://checkip.amazonaws.com");
//BufferedReader in = new BufferedReader(new InputStreamReader(
//                whatismyip.openStream()));
//
//String ip = in.readLine(); //you get the IP as a String
//System.out.println(ip);
//			 */
			URL whatIsMyIp = new URL("http://checkip.amazonaws.com");
			BufferedReader in = null;
			try {
				in = new BufferedReader(new InputStreamReader(whatIsMyIp.openStream()));
				ip = in.readLine();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			result.setIp(ip);
//			TextUtil utils = SpringUtils.getBean(TextUtil.class);
			String fileName = "Key.txt";
			if (StringUtils.isNotBlank(TextUtil.read(fileName))) {
				result.setMacAddress(TextUtil.read(fileName));
			} else {
				String key = RandomStringUtils.randomNumeric(20);
				TextUtil.write(fileName, key);
				result.setMacAddress(key);
			}
			// client.setIp("192.168.0.65");
//			NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

//			byte[] mac = network.getHardwareAddress();
//
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < mac.length; i++) {
//				sb.append(String.format("%s%02X", (i > 0 ? "-" : ""), mac[i]));
//			}
//			result.setMacAddress(sb.toString());
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder();
			sb.append("fetch ip mac error;");
			for (int i = 0; i < e.getStackTrace().length; i++) {
				sb.append(e.getStackTrace()[i]).append(";");
			}
			result.setNote(sb.toString());
		}
		return result;
	}

	/*
	從domain解析到IP
	 */

	public static String getIp(String url) {
		String result = "";
		try {
			String hostName = "";
			if (!url.startsWith("http://") && !url.startsWith("https://")) {
				URL urlObject = new URL("http://" + url);
				hostName = urlObject.getHost();
			} else {
				URL urlObject = new URL(url);
				hostName = urlObject.getHost();
			}
			List<InetAddress> addressList = new ArrayList<>(Arrays.asList(InetAddress.getAllByName(hostName)));
			if (!CollectionUtils.isEmpty(addressList)) {
				for (InetAddress addr : addressList) {
					if (addr instanceof Inet4Address) {
						result = addr.getHostAddress();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
//			log.error("网络无法链接，请检查...", e);
			return "无法解析域名";
		}
		return result;
	}
}
