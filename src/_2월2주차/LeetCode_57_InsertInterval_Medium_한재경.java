//구현
class LeetCode_57_InsertInterval_Medium_한재경 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> arr = new ArrayList<>();
        int n = intervals.length;
        int l = newInterval[0];
        int r = newInterval[1];

        //전
        int idx = 0;
        int s = l; //머지 시작점
        int e = r; //머지 끝점
        for (int i = 0; i < n; i++) {
            int il = intervals[i][0];
            int ir = intervals[i][1];
            if (il < l) { //시작지점 왼쪽보다 작은 경우 (포함안됨/전체감쌈/왼쪽겹침)
                if (ir < l) { //포함 안됨
                    arr.add(intervals[i]);
                    continue;
                }
                else if (ir > r) { //전체 감쌈
                    e = ir;
                }
                s = il;
                idx = i + 1;
                break;
            } else { //아예 초과
                idx = i;
                break;
            }
        }

        //중 (머지)
        for (int i = idx; i < n; i++) {
            int il = intervals[i][0];
            int ir = intervals[i][1];
            if (il <= r) { //머지 필요
                if (ir > r) { //우측 범위 벗어남
                    e = ir;
                }
                idx = i + 1;
            } else { //범위 아예 벗어남
                idx = i;
                break;
            }
        }
        arr.add(new int[]{s, e});

        //후
        for (int i = idx; i < n; i++) {
            arr.add(intervals[i]);
        }

        //리스트 -> 배열
        int[][] ans = new int[arr.size()][2];
        for (int i = 0; i < arr.size(); i++) {
            ans[i] = arr.get(i);
        }
        return ans;
    }
}
