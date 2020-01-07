import com.intellij.uiDesigner.core.GridConstraints
import com.intellij.uiDesigner.core.GridLayoutManager
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.SyntaxConstants
import org.fife.ui.rsyntaxtextarea.Theme

import javax.swing.*
import java.awt.*
import java.io.IOException

class Json2TsForm {
    var rootView: JPanel? = null
    var editor: RSyntaxTextArea? = null
    var generateButton: JButton?=null
    var finalFields: JCheckBox? = null
    var fileName: JTextField? = null
    var fileNameLabel: JLabel? = null

    private var listener: OnGenerateClicked? = null

    fun setOnGenerateListener(listener: OnGenerateClicked) {
        this.listener = listener
        generateButton!!.addActionListener {
            if (this.listener != null) {
                this.listener!!.onClicked(
                    if (fileName != null) fileName!!.text else "response",
                    if (editor != null) editor!!.text else ""
                )
            }
        }
    }

    private fun createUIComponents() {
        editor = RSyntaxTextArea()
        editor!!.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_JSON
        editor!!.isCodeFoldingEnabled = true
        try {
            val theme = Theme.load(
                javaClass.getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"
                )
            )
            theme.apply(editor!!)
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

    }

    init {
        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
        `$$$setupUI$$$`()
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private fun `$$$setupUI$$$`() {
        createUIComponents()
        rootView = JPanel()
        rootView!!.layout = GridLayoutManager(2, 4, Insets(0, 0, 0, 0), -1, -1)
        rootView!!.preferredSize = Dimension(500, 500)
        val scrollPane1 = JScrollPane()
        rootView!!.add(
            scrollPane1,
            GridConstraints(
                0,
                0,
                1,
                4,
                GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_WANT_GROW,
                null,
                null,
                null,
                0,
                false
            )
        )
        scrollPane1.setViewportView(editor)
        generateButton = JButton()
        generateButton!!.text = "Generate"
        rootView!!.add(
            generateButton,
            GridConstraints(
                1,
                3,
                1,
                1,
                GridConstraints.ANCHOR_CENTER,
                GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null,
                null,
                null,
                0,
                false
            )
        )
        finalFields = JCheckBox()
        finalFields!!.text = "Make fields final"
        rootView!!.add(
            finalFields!!,
            GridConstraints(
                1,
                0,
                1,
                1,
                GridConstraints.ANCHOR_WEST,
                GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK or GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null,
                null,
                null,
                0,
                false
            )
        )
        fileName = JTextField()
        rootView!!.add(
            fileName!!,
            GridConstraints(
                1,
                2,
                1,
                1,
                GridConstraints.ANCHOR_WEST,
                GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_FIXED,
                null,
                Dimension(150, -1),
                null,
                0,
                false
            )
        )
        fileNameLabel = JLabel()
        fileNameLabel!!.text = "Root file name:"
        rootView!!.add(
            fileNameLabel,
            GridConstraints(
                1,
                1,
                1,
                1,
                GridConstraints.ANCHOR_WEST,
                GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null,
                null,
                null,
                0,
                false
            )
        )
    }

    /**
     * @noinspection ALL
     */
    fun `$$$getRootComponent$$$`(): JComponent? {
        return rootView
    }

    interface OnGenerateClicked {
        fun onClicked(fileName: String, json: String)
    }

}
