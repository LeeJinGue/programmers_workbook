package get_declaration_result.src;

public class solution_main {
    public static void main(String[] args) throws Exception {
        Solution1 sol = new Solution1();
		String[] id_list = {"con", "ryan"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k = 3;
		
		sol.solution(id_list, report, k);
    }
}
