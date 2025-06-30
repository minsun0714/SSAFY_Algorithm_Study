import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testCase; i++) {
            Map<String, Group> map = new HashMap<>();
            int relationNum = Integer.parseInt(br.readLine());
            for (int j = 0; j < relationNum; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String first = st.nextToken();
                String second = st.nextToken();

                if (!map.containsKey(first) && !map.containsKey(second)) {
                    Group newGroup = new Group();
                    newGroup.addPerson(first).addPerson(second);
                    map.put(first, newGroup);
                    map.put(second, newGroup);
                } else if (map.containsKey(first) && map.containsKey(second)) {
                    Group group1 = map.get(first);
                    Group group2 = map.get(second);
                    if (group1 == group2) {
                        sb.append(group1.getSet().size()).append("\n");
                        continue;
                    }

                    // ✅ group2의 사람들을 group1으로 병합하고 참조도 바꿈
                    for (String name : group2.getSet()) {
                        group1.addPerson(name);
                        map.put(name, group1);
                    }
                } else if (map.containsKey(first)) {
                    Group group1 = map.get(first);
                    group1.addPerson(second);
                    map.put(second, group1);
                } else if (map.containsKey(second)) {
                    Group group2 = map.get(second);
                    group2.addPerson(first);
                    map.put(first, group2);
                }

                sb.append(map.get(first).getSet().size()).append("\n");
            }
        }

        System.out.print(sb);
    }

    static class Group {
        Set<String> set;

        public Group() {
            set = new HashSet<>();
        }

        public Group addPerson(String name) {
            set.add(name);
            return this;
        }

        public Set<String> getSet() {
            return this.set;
        }
    }
}
