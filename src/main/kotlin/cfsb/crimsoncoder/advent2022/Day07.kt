package cfsb.crimsoncoder.advent2022

class Day07(input: String) {
    private val data = parseInput(input)

    fun solveP1(): Long {
        // create directory tree
        val head = FileTreeStructure.create(data)

        // traverse every node, add all folder sizes less than 100_000
        return FileTreeStructure.getAllFolderSizes(head).filter { it <= 100_000L }.sum()
    }

    fun solveP2(): Long {
        val TOTAL_SIZE = 70_000_000L
        val TARGET_SIZE = 30_000_000L

        val head = FileTreeStructure.create(data)
        val rootSize = head.getFolderSize()
        val unusedSize = TOTAL_SIZE - rootSize
        val neededSize = TARGET_SIZE - unusedSize

        return FileTreeStructure.getAllFolderSizes(head).filter { it >= neededSize }.min()
    }


    private fun parseInput(input: String): List<String> {
        return input
            .trim()
            .split("\r\n")
    }
}

class FileTreeStructure {
    companion object {
        fun create (output: List<String>): FileTreeNode {
        val head = FileTreeNode("/")
        var curr = head
        for (line in output.subList(1, output.lastIndex+1)) {
            // if command
            if (line.startsWith("$")) {
                when {
                    //
                    line.startsWith("$ cd") -> {
                        when {
                            line.startsWith("$ cd ..") -> {
                                curr = if (curr.parent == null) {
                                    head
                                } else {
                                    curr.parent!!
                                }
                            }
                            line.startsWith("$ cd /") -> {
                                curr = head
                            }
                            else -> {
                                val dirName = line.split("$ cd ")[1]
                                curr = curr.children.find { it.dirName == dirName }!!
//                                curr = curr.addSubdirectory(FileTreeNode(dirName))
                            }
                        }
                    }

                    line.startsWith("$ ls") -> {}
                }
            } else {
                when {
                    line.startsWith("dir") -> {
                        val dirName = line.split("dir ")[1]
                        curr.addSubdirectory(FileTreeNode(dirName))
                    }

                    else -> {
                        val (size, name) = line.split(" ", limit = 2)
                        curr.addFile(FileInfo(name = name, size = size.toLong()))
                    }
                }
            }

        }
        return head
    }

        @Deprecated("")
        fun calculateSumOfFolderSizes (node: FileTreeNode, maxSize: Long): Long {

            // if no files and no subdirectories, just return 0
            if (node.children.size == 0 && node.files.size == 0) return 0L

            // if no subdirectories but some files, calculate size and return it if it's less than maxSize
            else if (node.children.size == 0) {
                val folderSize = node.getFolderSize()
                return if (folderSize <= maxSize) {
                    folderSize
                } else {
                    0L
                }
            }

            // if some files and some subdirectories, calculate size and return it (plus the size of all children) if it's less than maxSize
            else {
                val folderSize = node.getFolderSize()
                return if (folderSize <= maxSize) {
                    folderSize + node.children.sumOf { calculateSumOfFolderSizes(it, maxSize) }
                } else {
                    node.children.sumOf { calculateSumOfFolderSizes(it, maxSize) }
                }
            }

        }

        fun getAllFolderSizes (node: FileTreeNode): List<Long> {
            if (node.children.size == 0 && node.files.size == 0) return emptyList()
            else if (node.children.size == 0) return listOf(node.getFolderSize())
            else return listOf(node.getFolderSize()) + node.children.fold(listOf()) { acc, fileTreeNode ->
                acc + getAllFolderSizes(fileTreeNode)
            }
        }

    }
}

data class FileInfo (val name: String, val size: Long)
class FileTreeNode (val dirName: String) {
    var parent: FileTreeNode? = null
    val files = mutableListOf<FileInfo>()
    val children = mutableListOf<FileTreeNode>()

    fun addFile (info: FileInfo) {
        files.add(info)
    }

    fun addSubdirectory (node: FileTreeNode): FileTreeNode {
        children.add(node)
        node.parent = this
        return children.find { it == node }!!
    }

    fun getFolderSize (): Long {
        return _getFolderSize(this)
    }

    private fun _getFolderSize (node: FileTreeNode): Long {
        if (node.files.size == 0 && node.children.size == 0) return 0L
        if (node.files.size == 0) return node.children.sumOf { this._getFolderSize(it) }
        return node.files.sumOf { it.size } + node.children.sumOf { this._getFolderSize(it) }
    }

}