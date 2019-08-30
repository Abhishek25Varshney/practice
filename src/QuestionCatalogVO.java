import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QuestionCatalogVO {
	private Map<String,Set<Integer>> keyToQuestionIds;
	private Map<String,Set<Integer>> keyToPSQuestionIds;
	private Map<String,Set<Integer>> keyToDSQuestionIds;
	
	public Map<String, Set<Integer>> getKeyToQuestionIds() {
		return keyToQuestionIds;
	}

	public void setKeyToQuestionIds(Map<String, Set<Integer>> keyToQuestionIds) {
		this.keyToQuestionIds = keyToQuestionIds;
	}

	public Map<String, Set<Integer>> getKeyToPSQuestionIds() {
		return keyToPSQuestionIds;
	}

	public void setKeyToPSQuestionIds(Map<String, Set<Integer>> keyToPSQuestionIds) {
		this.keyToPSQuestionIds = keyToPSQuestionIds;
	}

	public Map<String, Set<Integer>> getKeyToDSQuestionIds() {
		return keyToDSQuestionIds;
	}

	public void setKeyToDSQuestionIds(Map<String, Set<Integer>> keyToDSQuestionIds) {
		this.keyToDSQuestionIds = keyToDSQuestionIds;
	}

}
