package cscie97.asn4.housemate.model;

import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.entitlement.HouseMateEntitlementImpl;
import cscie97.asn4.housemate.entitlement.HouseMateEntitlementFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This is to provide command line interface and file input.
 * 
 * @author ying
 *
 */

public class HouseMateCLI {

	/**
	 * method for basic format check of the command
	 *
	 * @param command
	 * @return tokenized command
	 */
	public static String[] checkCommand(String command) {
		// show the input String command
		System.out.println("");
	//	System.out.println("input command: " + command);
	//	System.out.println("*********************************************");

		command = command.trim();
		String[] tokens;
		tokens = command.split("\\s+");

		if (CommandKeyWord.contains(tokens[0]) ) {
			return tokens;
		} else
			return null;
	}

	/**
	 * method for input file
	 *
	 * @param fileName
	 */
	public static void importConfigFile(String fileName) throws IOException {
		int commandCount = 0;
		HouseMateEntitlementImpl entitle = HouseMateEntitlementFactory.getInstance();
		BufferedReader br = null;
		String line;
		String modifiedLine;
		AccessToken auth_token = null;
		String EntitlementID = "admin_role";
		try {
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				commandCount++;

				if (line.indexOf('#') != -1) {
					continue;
				}
				// replace "." with whitespace and then get rid of all the
				// whitespace in the end of the line.
				modifiedLine = line.trim();

				if (modifiedLine.length() == 0)
					continue;

				if(line.contains("login") && !line.contains("#")){
					String[] logInInfo = line.split(" ");
					auth_token = entitle.logIn(logInInfo[1], logInInfo[2], logInInfo[3]);
//					if(entitle.checkAccess(auth_token,EntitlementID)){
//						System.out.println("*");
//						continue;
//					}
				}else{
			//		System.out.println("******************************************");
				//	System.out.print(commandCount + ".  ");
					CommandParser.executeCommand(modifiedLine, auth_token,commandCount );

				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (IOException e) {
			System.out.println("Unable to read file: " + fileName);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Unable to close file: " + fileName);
			} catch (NullPointerException e) {
				System.out.println("NullPointException");
			}
		}

	}

}


/**
 * method for providing command line interface
 */

//	public static void comLineInterface() {
//		Scanner scanner = null;
//		AccessToken auth_token = null;
//		try {
//			scanner = new Scanner(System.in);
//			// rest of the code
//			String s = "";
//			System.out.println("****************************************");
//			System.out.println("Welcome to House Mate command line interface");
//			System.out.println("Please enter your command. Enter \"exit\" to terminate");
//
//			while (!(s = scanner.nextLine()).equals("exit")) {
//				try {
//					CommandParser.executeCommand(s, auth_token);
//				} catch (AccessDeniedException e) {
//					e.print();
//				} catch (AuthenticationException e) {
//					e.print();
//				}
//				System.out.println("Please enter your command");
//			}
//		} finally {
//			if (scanner != null)
//				scanner.close();
//		}
//	}
