package com.melonltd.naber.rdbms.model.push.service;

public class GmailQuickstart {
//	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
//	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//	private static final String TOKENS_DIRECTORY_PATH = "tokens";
//
//	/**
//	 * Global instance of the scopes required by this quickstart. If modifying these
//	 * scopes, delete your previously saved tokens/ folder.
//	 */
//	private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_LABELS);
//	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
//
//	/**
//	 * Creates an authorized Credential object.
//	 * 
//	 * @param HTTP_TRANSPORT
//	 *            The network HTTP Transport.
//	 * @return An authorized Credential object.
//	 * @throws IOException
//	 *             If the credentials.json file cannot be found.
//	 */
//	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//		// Load client secrets.
//		InputStream in = GmailQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//		// Build flow and trigger user authorization request.
//		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
//				clientSecrets, SCOPES)
//						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//						.setAccessType("offline").build();
//		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//	}
//
//	public static void main(String... args) throws IOException, GeneralSecurityException {
//		// Build a new authorized API client service.
//		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//				.setApplicationName(APPLICATION_NAME).build();
//
//		// Print the labels in the user's account.
//		String user = "me";
//		ListLabelsResponse listResponse = service.users().labels().list(user).execute();
//		List<Label> labels = listResponse.getLabels();
//		if (labels.isEmpty()) {
//			System.out.println("No labels found.");
//		} else {
//			System.out.println("Labels:");
//			for (Label label : labels) {
//				System.out.printf("- %s\n", label.getName());
//			}
//		}
//	}
//
//	
//	/**
//     * Create a MimeMessage using the parameters provided.
//     *
//     * @param to email address of the receiver 收件信箱
//     * @param from email address of the sender, the mailbox account 發件信箱
//     * @param subject subject of the email 主題
//     * @param bodyText body text of the email 郵件正文
//     * @return the MimeMessage to be used to send email 
//     * @throws MessagingException
//     */
//	public static MimeMessage createEmail(String to, String from, String subject, String bodyText)
//			throws MessagingException {
//		Properties props = new Properties();
//		Session session = Session.getDefaultInstance(props, null);
//
//		MimeMessage email = new MimeMessage(session);
//
//		email.setFrom(new InternetAddress(from));
//		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
//		email.setSubject(subject);
//		email.setText(bodyText);
//		return email;
//	}
//
//	public static Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
//		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//		emailContent.writeTo(buffer);
//		byte[] bytes = buffer.toByteArray();
//		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
//		Message message = new Message();
//		message.setRaw(encodedEmail);
//		return message;
//	}
//
//	
//	/**
//     * Send an email from the user's mailbox to its recipient.
//     *
//     * @param service Authorized Gmail API instance.
//     * @param userId User's email address. The special value "me"
//     * can be used to indicate the authenticated user.
//     * @param emailContent Email to be sent.
//     * @return The sent message
//     * @throws MessagingException
//     * @throws IOException
//     */
//	public static Message sendMessage(String userId, MimeMessage emailContent) throws MessagingException, IOException, GeneralSecurityException {
//		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//				.setApplicationName(APPLICATION_NAME).build();
//		Message message = createMessageWithEmail(emailContent);
//		message = service.users().messages().send(userId, message).execute();
//
//		System.out.println("Message id: " + message.getId());
//		System.out.println(message.toPrettyString());
//		return message;
//	}
}