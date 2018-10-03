import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinedLogic {

	public static void main(String[] args) {
		List<Integer> eligiblePackages = new ArrayList<>();
		eligiblePackages.add(40);
		eligiblePackages.add(50);
		 eligiblePackages.add(20);
		// eligiblePackages.add(160);
		// eligiblePackages.add(30);
		Map<Integer, String> mapOfCombinedCourses = new HashMap<Integer, String>();
		int k = 0;
		for (int i = 0; i < eligiblePackages.size(); i++) {

			Integer firstCourseId = eligiblePackages.get(i);

			Integer secondCourseId = 0;
			for (int j = 0; j < eligiblePackages.size(); j++) {

				secondCourseId = eligiblePackages.get(j);
				if (firstCourseId != secondCourseId) {
					System.out.println(
							"Combined Course Combination generated [" + firstCourseId + " " + secondCourseId + "]");
					if (!(mapOfCombinedCourses
							.containsValue(String.valueOf(firstCourseId) + "|" + String.valueOf(secondCourseId))
							|| mapOfCombinedCourses.containsValue(
									String.valueOf(secondCourseId) + "|" + String.valueOf(firstCourseId)))) {
						mapOfCombinedCourses.put(k,
								String.valueOf(firstCourseId) + "|" + String.valueOf(secondCourseId));
						k++;
					} else {

					}
				}
			}

		}
		for (Map.Entry<Integer, String> entry : mapOfCombinedCourses.entrySet()) {
			if (entry.getValue().equals("40|50")||entry.getValue().equals("50|40")||entry.getValue().equals("20|50")||entry.getValue().equals("20|50")) {
				mapOfCombinedCourses.remove(entry.getKey());
				System.out.println("Combo store " + entry.getValue());
			}
			System.out.println("Combo store " + entry.getValue());
		}
		for (Map.Entry<Integer, String> entry : mapOfCombinedCourses.entrySet()) {
			System.out.println(entry.getValue());
		}
	}

}
