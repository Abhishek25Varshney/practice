import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryGenerator {

	public static List<String> output = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		String[] userData = "manangupta123@gmail.com,2".split("\\|");
//		getLMSUserQuery(userData);
//		output.add("==========================================");
//		output.add("==========================================");
//		output.add("==========================================");
//		getUserCourseEnrollmentQuery(userData);

//		output.add("==========================================");
//		output.add("==========================================");
//		output.add("==========================================");
//		getScholUserQuery(userData);

//		output.add("==========================================");
//		output.add("==========================================");
//		output.add("==========================================");
		getMockAllotmentQuery(userData);
		writeUsingFiles();

	}

	public static void getLMSUserQuery(String[] userData) {
		List<String> query = new LinkedList<>();
		query.add(
				"INSERT INTO lms_user (first_name, last_name, status, creation_date, last_modified_date, password, username,is_coreg_user,is_locked,email_auth,google_auth,fb_auth,has_seen_getting_started) VALUES (' ',' ','ENABLED',now(),now(),'${password}','${username}',false,false,false,false,false,false);");
		query.add(
				"INSERT INTO lms_user_role (user_id, role_id) VALUES ((Select id from lms_user where username='${username}'), 1);");

		for (String string : userData) {
			String username = string.split(",")[0];
			String password = string.split(",")[1];
			Map<String, String> map = new HashMap<>();
			map.put("username", username);
			map.put("password", password);
			StrSubstitutor str = new StrSubstitutor(map);
			for (String q : query) {
				// System.out.println(str.replace(q));
				output.add(str.replace(q));
			}

		}

	}

	public static void getScholUserQuery(String[] userData) {
		List<String> query = new LinkedList<>();
		query.add(
				"INSERT INTO es_user (email, user_external_id, first_name, last_name, status, creation_date, subscription_start_time, subscription_end_time, last_modified_date, subscription_status, enabled, password, username, is_test_user, quant_subscription_status, quant_subscription_start_time, quant_subscription_end_time) VALUES ('', '', '', ' ', 'ENABLED', '2017-07-07 18:39:12', '2017-07-07 18:39:12', '1970-01-01 00:00:00', '2017-07-07 18:39:12', 'FREETRIAL', b'1', '${password}', '${username}', b'0', 'FREETRIAL', '2017-07-07 18:39:12', '1970-01-01 00:00:00');");
		query.add(
				"INSERT INTO es_user_role (user_id, role_id) VALUES ((Select id from es_user where username='${username}'), 1);");

		for (String string : userData) {
			String username = string.split(",")[0];
			String password = string.split(",")[1];
			Map<String, String> map = new HashMap<>();
			map.put("username", username);
			map.put("password", password);
			StrSubstitutor str = new StrSubstitutor(map);
			for (String q : query) {
				// System.out.println(str.replace(q));
				output.add(str.replace(q));
			}

		}

	}

	public static void getUserCourseEnrollmentQuery(String[] userData) {
		List<String> query = new LinkedList<>();
		query.add(
				"INSERT INTO lms_user_package_subscription_log (user_id, base_package_id, timestamp, subscription_start_date, subscription_end_date, duration_in_days, course_package_id, package_identifier, subscription_balance, subscription_state, subscription_type, extension_comment, subscription_notification_shown) VALUES ((Select id from lms_user where username='${username}'), 420, now(), now(),'2020-04-10 00:00:00', 31, 420, '420', 0, 'APPROVED', 'OFFER', NULL, 'THIRD_NOTIFICATION');");
		query.add(
				"INSERT INTO lms_user_course_enrollment (user_id, course_id, subscription_start_time, subscription_end_time, timestamp, Enrollment_type, status) VALUES ((Select id from lms_user where username='${username}'), 30, now(), '2020-04-10 00:00:00', now(), 'AUTO', 'ENABLED');");

		for (String string : userData) {
			String username = string.split(",")[0];
			Map<String, String> map = new HashMap<>();
			map.put("username", username);
			StrSubstitutor str = new StrSubstitutor(map);
			for (String q : query) {
				// System.out.println(str.replace(q));
				output.add(str.replace(q));
			}

		}

	}

	public static void getMockAllotmentQuery(String[] userData) {
		List<String> query = new LinkedList<>();
		query.add(
				"INSERT INTO es_quiz (user_id, name, total_number_of_questions, status, quiz_mode, number_of_questions_attempted, score, max_score, time_taken, quiz_ability, answered_correct, answered_incorrect, max_time, creation_date, quiz_type, template_id, actual_time_taken, quiz_end_mode, verbal_end_mode, quant_end_mode, section, percentile, mock_id, verbal_target_score, quant_target_score, first_section_instruction, second_section_instruction, optional_break_time) VALUES ((Select id from es_user where username='${username}'), 'Mock ${mockId}', 0, 'COMPLETED', 'QUIZ', 0, 0.00, 0.00, 0, 0.00, 0, 0, 0, now(), 'MOCK', NULL, 00, NULL, 'FULLYCOMPLETE', 'FULLYCOMPLETE', 'VERBAL', 0,${mockId}, 0, 0, 'SYSTEM', 'SYSTEM', 0);");
		query.add(
				"INSERT INTO es_mock_student_report (user_id, quiz_id, attempted_date, overall_score, overall_percentile, overall_target_score, verbal_score, sc_score, cr_score, rc_score, ar_score, alg_geo_score, verbal_target_score, verbal_percentile, verbal_avg_correct_time, verbal_avg_incorrect_time, verbal_subject_target_score, sc_percentile, sc_percentage, cr_percentile, cr_percentage, rc_percentile, rc_percentage, quant_score, quant_target_score, quant_percentile, quant_avg_correct_time, quant_avg_incorrect_time, quant_subject_target_score, ar_percentile, ar_percentage, ar_ds_accuracy, ar_ps_accuracy, alg_geo_percentile, alg_geo_percentage, alg_geo_ds_accuracy, alg_geo_ps_accuracy) VALUES ((Select id from es_user where username='${username}'), (Select id from es_quiz where mock_id=${mockId} and user_id=(Select id from es_user where username='${username}')), now(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);");

		for (String string : userData) {
			String username = string.split(",")[0];
			String requiredMockId = string.split(",")[1];
			Map<String, String> map = new HashMap<>();
			map.put("username", username);
			for (int i = 1; i < 6; i++) {
				if (Integer.parseInt(requiredMockId) == i) {
					continue;
				}
				map.put("mockId", String.valueOf(i));
				StrSubstitutor str = new StrSubstitutor(map);
				for (String q : query) {
					// System.out.println(str.replace(q));
					output.add(str.replace(q));
				}
			}

		}

	}

	private static void writeUsingFiles() throws IOException {
		try {
			FileWriter fileWriter = new FileWriter("F:\\Output.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (String string : output) {
				bufferedWriter.write(string);
				bufferedWriter.newLine();
			}

			// Always close files.
			bufferedWriter.close();

		} catch (IOException ex) {
		}
	}

}

class StrSubstitutor {
	private Map<String, String> map;
	private Pattern p = Pattern.compile("\\$\\{(.+?)\\}");

	public StrSubstitutor(Map<String, String> map) {
		this.map = map;
	}

	public String replace(String str) {
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String var = m.group(1);
			String replacement = map.get(var);
			m.appendReplacement(sb, replacement);
		}
		m.appendTail(sb);
		return sb.toString();
	}
}