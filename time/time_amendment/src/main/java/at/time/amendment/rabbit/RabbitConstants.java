package at.time.amendment.rabbit;

public final class RabbitConstants {

	// Connection
	public static final String HOST = "localhost";
	public static final int PORT = 5672;

	// Exchange Types
	public static final String DIRECT = "direct";
	public static final String FANOUT = "fanout";

	// Exchanges
	public static final String TIME_EXCHANGE = "timeExchange";

	// Queues
	public static final String REPORT_QUEUE = "reportQueue";
	public static final String RECORD_QUEUE = "recordQueue";
	public static final String AMENDMENT_QUEUE = "amendmentQueue";

	// Content Types
	public static final String CT_USER = "user";
	public static final String CT_RECORD = "record";

}
