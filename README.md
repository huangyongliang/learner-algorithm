# learner-algorithm
learn the algorithm in Java


| 算法 | 实现类 |
|:---|:---|
|桶排序|com.hyl.algorithm.core.impl.BucketStrategy|
|冒泡排序|com.hyl.algorithm.core.impl.BubbleStrategy|
|快速排序|com.hyl.algorithm.core.impl.QuicksortStrategy|

测试类：`com.hyl.algorithm.core.StrategyTest`

最短路径算法对比分析

| | Floyd | Dijkstra | Bellman-Ford | 队列优化的Bellman-Ford |
|:---:|:---:|:---:|:---:|:---:|
| 空间复杂度 | O(NN) | O(M) | O(NM) | O(M) |
| 时间复杂度 | O(NNN) | O((M+N)logN) | O(NM) | O(M)~O(NM) |
| 适用情况 | 稠密图和顶点关系密切 | 稠密图和顶点关系密切 | 稀疏图和边关系密切 | 稀疏图和边关系密切 |
| 负权 | 可以解决负权 | 不能解决负权 | 可以解决负权 | 可以解决负权 |
| 有负权边 | 可以处理 | 不能处理 | 可以处理 | 可以处理 |
| 判断是否存在负权回路 | 不能 | 不能 | 可以判定 | 可以判定 |

