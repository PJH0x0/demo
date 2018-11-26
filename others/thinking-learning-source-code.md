学习源码的一些想法
### 结构
首先是要大致的浏览一遍代码，梳理流程，这一遍要达到这几个目的
1. 源码的目录和文件大致分在哪些部分
2. 使用了哪些技术要能够大致的清楚
3. 大致的流程要能够清楚
这些目的也许有部分都是重复的，并且有些需要参照一些博客和文档才能理清楚

### 细节
这个部分主要是要有一份详细流程的文档，建议是配合uml时序图。
使用文档有这些个好处:
1. 细节方面更加清楚
2. 后面忘记了，看到了也可以快速想起来

文档的要求
1. 要分层次，假设以每个业务流程来作为区分的话。
可以横向分层，即以知识点的深度来分层。也可以纵向分层，即以每个流程来作为不同的层次
2. 代码要有对应的文件名以及类名
3. 代码的注释应当要清楚的解释每行代码的功能

文档的检查
1. 自己要能看的懂
2. 检查语句是否通顺，特别注意**不能有歧义**
