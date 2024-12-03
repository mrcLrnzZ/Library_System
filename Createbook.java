import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

 class Createbook {
    private JFrame frame;
    private JTextField bookNumberField, bookTitleField, bookAuthorField;

    public Createbook(JFrame parentFrame, JFrame adminFrame) {
        frame = new JFrame("Create Book");
        frame.setIconImage(new ImageIcon("White and Blue Illustrative Class Logo-modified.png").getImage());

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("bgpictttt.png");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel headerLabel = new JLabel("Create New Book");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        headerLabel.setForeground(new Color(0x3B3030));
        headerLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(headerLabel);

        JLabel bookNumberLabel = new JLabel("Book Number");
        bookNumberLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(bookNumberLabel);
        bookNumberField = new JTextField();
        panel.add(bookNumberField);

        JLabel bookTitleLabel = new JLabel("Book Title");
        bookTitleLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(bookTitleLabel);
        bookTitleField = new JTextField();
        panel.add(bookTitleField);

        JLabel bookAuthorLabel = new JLabel("Book Author");
        bookAuthorLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(bookAuthorLabel);
        bookAuthorField = new JTextField();
        panel.add(bookAuthorField);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        submitButton.setBackground(new Color(0x603F26));
        submitButton.setForeground(Color.WHITE);
        submitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookNumber = bookNumberField.getText();
                String bookTitle = bookTitleField.getText();
                String bookAuthor = bookAuthorField.getText();

                if (bookNumber.isEmpty() || bookTitle.isEmpty() || bookAuthor.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled!");
                    return;
                }
                if (isDuplicateBookNumber(bookNumber)) {
                 JOptionPane.showMessageDialog(frame, "The book number already exists! Please use a different book number.");
                    return;
                }
                saveBookDetails(bookNumber, bookTitle, bookAuthor);
                JOptionPane.showMessageDialog(frame, "Book created successfully!");
                frame.dispose();
               
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        backButton.setBackground(new Color(0x603F26));
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.setMaximumSize(new Dimension(100, 40));
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.setFocusPainted(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                adminFrame.setVisible(true); 
            }
        });

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(Box.createHorizontalGlue());
        horizontalBox.add(Box.createRigidArea(new Dimension(10, 0)));
        horizontalBox.add(backButton);
        horizontalBox.add(Box.createRigidArea(new Dimension(20, 0)));

        panel.add(horizontalBox);

        frame.add(panel);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(parentFrame);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void saveBookDetails(String bookNumber, String bookTitle, String bookAuthor) {
        

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt", true))) {
            writer.write(bookNumber + "," + bookTitle + "," + bookAuthor);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving book details: " + e.getMessage());
        }
        
    }


    private boolean isDuplicateBookNumber(String bookNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",", 3); 
                if (details[0].equals(bookNumber)) {
                    return true; 
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file: " + e.getMessage());
        }
        return false; 
    }
    
}
