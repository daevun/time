package at.time.base.rabbit;

public final class RabbitConstants {

	// Connection
	public static String HOST = "localhost";
	public static int PORT = 5672;

	// Exchange Types
	public static String DIRECT = "direct";
	public static String FANOUT = "fanout";

	// Exchanges
	public static String TIME_EXCHANGE = "timeExchange";

	// Queues
	public static String REPORT_QUEUE = "reportQueue";
	public static String RECORD_QUEUE = "recordQueue";
	public static String AMENDMENT_QUEUE = "amendmentQueue";

}
