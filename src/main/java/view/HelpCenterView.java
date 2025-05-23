package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * HelpCenterView - A Java Swing implementation of a Help Center interface.
 * This class displays a Help Center page with a sidebar navigation and multiple content areas
 * including FAQ, Live Support, and Feedback.
 */
public class HelpCenterView extends JFrame {

    // UI Components
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    // Content panels
    private JPanel faqPanel;
    private JPanel liveSupportPanel;
    private JPanel feedbackPanel;
    
    // Sidebar items
    private JPanel accountPanel;
    private JPanel billingPanel;
    private JPanel budgetPanel;
    private JPanel helpCenterPanel;
    private JPanel financialAdvisorPanel;
    private JPanel faqItemPanel;
    private JPanel liveSupportItemPanel;
    private JPanel feedbackItemPanel;
    
    // Help Center sub-items container
    private JPanel helpCenterSubItems;
    private boolean helpCenterExpanded = true;
    
    // Colors and styling
    private final Color PRIMARY_BLUE = new Color(52, 152, 219);
    private final Color LIGHT_GRAY = new Color(245, 245, 245);
    private final Color DARK_GRAY = new Color(100, 100, 100);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 22);
    private final Font SIDEBAR_FONT = new Font("Arial", Font.PLAIN, 14);
    private final Font SUBHEADER_FONT = new Font("Arial", Font.BOLD, 18);
    private final Font CONTENT_FONT = new Font("Arial", Font.PLAIN, 14);
    
    /**
     * Constructor for the HelpCenterView
     */
    public HelpCenterView() {
        setTitle("Help Center");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create components
        createHeader();
        createSidebar();
        createContentPanel();
        createFAQPanel();
        createLiveSupportPanel();
        createFeedbackPanel();
        
        // Show FAQ panel by default
        cardLayout.show(contentPanel, "FAQ");
        setActiveSidebarItem(faqItemPanel);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Creates the header panel with the Help Center title
     */
    private void createHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_BLUE);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 50));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Help Center");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        // Create logout button in the top-right corner
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(231, 76, 60)); // Red color
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setFocusPainted(false);
        logoutButton.setOpaque(true);
        logoutButton.setBorderPainted(false);
        
        // Create a panel to hold the logout button with some margin
        JPanel logoutPanel = new JPanel();
        logoutPanel.setBackground(PRIMARY_BLUE);
        logoutPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        logoutPanel.add(logoutButton);
        
        // Add action listener to logout button
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close current window
                new LoginView(); // Return to login screen
            }
        });
        
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(logoutPanel, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
    }
    
    /**
     * Creates the sidebar navigation panel
     */
    private void createSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setBackground(Color.WHITE);
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        
        // Add sidebar items without icons
        accountPanel = createSidebarItem("Account", false, null);
        billingPanel = createSidebarItem("Billing & Subscriptions", false, null);
        budgetPanel = createSidebarItem("Budget", false, null);
        financialAdvisorPanel = createSidebarItem("Financial Advisor", false, null);
        
        // Help Center with expandable/collapsible sub-items
        helpCenterPanel = createSidebarItem("Help Center", false, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleHelpCenterSubItems();
            }
        });
        
        // Create Help Center sub-items
        helpCenterSubItems = new JPanel();
        helpCenterSubItems.setLayout(new BoxLayout(helpCenterSubItems, BoxLayout.Y_AXIS));
        helpCenterSubItems.setBackground(Color.WHITE);
        helpCenterSubItems.setVisible(helpCenterExpanded);
        
        // Create sub-items
        faqItemPanel = createSidebarItem("FAQ", true, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentPanel, "FAQ");
                setActiveSidebarItem(faqItemPanel);
            }
        });
        
        liveSupportItemPanel = createSidebarItem("Live Support", true, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentPanel, "LiveSupport");
                setActiveSidebarItem(liveSupportItemPanel);
            }
        });
        
        feedbackItemPanel = createSidebarItem("Feedback", true, new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentPanel, "Feedback");
                setActiveSidebarItem(feedbackItemPanel);
            }
        });
        
        // Add click listeners to main navigation items
        accountPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new AccountView();
            }
        });
        
        billingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new BillingView();
            }
        });
        
        budgetPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new BudgetView();
            }
        });
        
        // Add click listener for Financial Advisor
        financialAdvisorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new FinancialAdvisorView();
            }
        });
        
        // Add sub-items to the container
        helpCenterSubItems.add(faqItemPanel);
        helpCenterSubItems.add(Box.createVerticalStrut(5));
        helpCenterSubItems.add(liveSupportItemPanel);
        helpCenterSubItems.add(Box.createVerticalStrut(5));
        helpCenterSubItems.add(feedbackItemPanel);
        
        // Add sidebar items to panel
        sidebarPanel.add(accountPanel);
        sidebarPanel.add(Box.createVerticalStrut(5));
        sidebarPanel.add(billingPanel);
        sidebarPanel.add(Box.createVerticalStrut(5));
        sidebarPanel.add(budgetPanel);
        sidebarPanel.add(Box.createVerticalStrut(5));
        sidebarPanel.add(financialAdvisorPanel);
        sidebarPanel.add(Box.createVerticalStrut(5));
        sidebarPanel.add(helpCenterPanel);
        sidebarPanel.add(helpCenterSubItems);
        
        // Add a glue component to push everything to the top
        sidebarPanel.add(Box.createVerticalGlue());
        
        // Add sidebar to main frame
        add(sidebarPanel, BorderLayout.WEST);
    }
    
    /**
     * Toggle the visibility of Help Center sub-items
     */
    private void toggleHelpCenterSubItems() {
        helpCenterExpanded = !helpCenterExpanded;
        helpCenterSubItems.setVisible(helpCenterExpanded);
        
        // Add arrow indicator to Help Center item
        JLabel helpCenterLabel = (JLabel) ((JPanel) helpCenterPanel.getComponent(0)).getComponent(0);
        helpCenterLabel.setText("Help Center " + (helpCenterExpanded ? "▼" : "▶"));
        
        // Refresh the UI
        sidebarPanel.revalidate();
        sidebarPanel.repaint();
    }
    
    /**
     * Creates a sidebar item
     * @param text The text for the sidebar item
     * @param isSubItem Whether this is a sub-item (indented)
     * @return The created panel
     */
    private JPanel createSidebarItem(String text, boolean isSubItem, MouseAdapter clickListener) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        // Inner panel to hold the label for proper alignment
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        labelPanel.setBackground(Color.WHITE);
        
        String displayText = text;
        if (text.equals("Help Center")) {
            displayText = text + " " + (helpCenterExpanded ? "▼" : "▶");
        }
        
        JLabel textLabel = new JLabel(displayText);
        textLabel.setFont(SIDEBAR_FONT);
        textLabel.setForeground(DARK_GRAY);
        
        if (isSubItem) {
            labelPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
        } else {
            labelPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        }
        
        labelPanel.add(textLabel);
        itemPanel.add(labelPanel, BorderLayout.CENTER);
        
        // Add hover effect
        itemPanel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (itemPanel.getBackground() != LIGHT_GRAY) {
                    itemPanel.setBackground(new Color(250, 250, 250));
                    labelPanel.setBackground(new Color(250, 250, 250));
                }
            }
            
            public void mouseExited(MouseEvent evt) {
                if (itemPanel.getBackground() != LIGHT_GRAY) {
                    itemPanel.setBackground(Color.WHITE);
                    labelPanel.setBackground(Color.WHITE);
                }
            }
        });
        
        // Add click listener if provided
        if (clickListener != null) {
            itemPanel.addMouseListener(clickListener);
        }
        
        return itemPanel;
    }
    
    /**
     * Sets the active sidebar item by changing its background color
     * @param activePanel The panel to set as active
     */
    private void setActiveSidebarItem(JPanel activePanel) {
        // Reset all panels
        faqItemPanel.setBackground(Color.WHITE);
        faqItemPanel.getComponent(0).setBackground(Color.WHITE);
        liveSupportItemPanel.setBackground(Color.WHITE);
        liveSupportItemPanel.getComponent(0).setBackground(Color.WHITE);
        feedbackItemPanel.setBackground(Color.WHITE);
        feedbackItemPanel.getComponent(0).setBackground(Color.WHITE);
        
        // Set active panel
        activePanel.setBackground(LIGHT_GRAY);
        activePanel.getComponent(0).setBackground(LIGHT_GRAY);
    }
    
    /**
     * Creates the main content panel with card layout to switch between different content panels
     */
    private void createContentPanel() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);
        add(contentPanel, BorderLayout.CENTER);
    }
    
    /**
     * Creates the FAQ content panel
     */
    private void createFAQPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Create FAQ header
        JLabel faqHeaderLabel = new JLabel("Frequently Asked Questions");
        faqHeaderLabel.setFont(SUBHEADER_FONT);
        faqHeaderLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create FAQ content panel
        faqPanel = new JPanel();
        faqPanel.setBackground(Color.WHITE);
        faqPanel.setLayout(new BoxLayout(faqPanel, BoxLayout.Y_AXIS));
        faqPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        // Add FAQ questions
        addFAQItem("How do I connect my bank accounts?");
        addFAQItem("How does the AI analyze my spending habits?");
        addFAQItem("Is my financial data secure?");
        addFAQItem("How can I set up budget alerts?");
        
        // Add to main content
        mainPanel.add(faqHeaderLabel, BorderLayout.NORTH);
        
        // Add FAQ panel to a scroll pane
        JScrollPane scrollPane = new JScrollPane(faqPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        contentPanel.add(mainPanel, "FAQ");
    }
    
    /**
     * Creates the Live Support content panel
     */
    private void createLiveSupportPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Create Live Support header
        JLabel supportHeaderLabel = new JLabel("Live Support");
        supportHeaderLabel.setFont(SUBHEADER_FONT);
        supportHeaderLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create Live Support content
        JPanel supportContentPanel = new JPanel();
        supportContentPanel.setLayout(new BoxLayout(supportContentPanel, BoxLayout.Y_AXIS));
        supportContentPanel.setBackground(Color.WHITE);
        supportContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add support info
        JLabel supportInfoLabel = new JLabel("Our support team is ready to assist you with any questions about your AI-powered finance tracker.");
        supportInfoLabel.setFont(CONTENT_FONT);
        supportInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel hoursLabel = new JLabel("Support Hours: Monday to Friday, 9:00 - 18:00");
        hoursLabel.setFont(CONTENT_FONT);
        hoursLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hoursLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        JButton chatButton = new JButton("Start Chat");
        chatButton.setBackground(PRIMARY_BLUE);
        chatButton.setForeground(Color.WHITE);
        chatButton.setFont(CONTENT_FONT);
        chatButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        chatButton.setMaximumSize(new Dimension(150, 40));
        chatButton.setFocusPainted(false);
        chatButton.setOpaque(true);
        chatButton.setBorderPainted(false);
        
        JLabel contactLabel = new JLabel("You can also reach us through:");
        contactLabel.setFont(CONTENT_FONT);
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        JLabel emailLabel = new JLabel("Email: nzy1522512646@gmail.com");
        emailLabel.setFont(CONTENT_FONT);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel phoneLabel = new JLabel("Phone: XXX-XXXX-XXXX");
        phoneLabel.setFont(CONTENT_FONT);
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        phoneLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        // Add components to panel
        supportContentPanel.add(supportInfoLabel);
        supportContentPanel.add(hoursLabel);
        supportContentPanel.add(chatButton);
        supportContentPanel.add(contactLabel);
        supportContentPanel.add(emailLabel);
        supportContentPanel.add(phoneLabel);
        
        // Add to main panel
        mainPanel.add(supportHeaderLabel, BorderLayout.NORTH);
        mainPanel.add(supportContentPanel, BorderLayout.CENTER);
        
        contentPanel.add(mainPanel, "LiveSupport");
    }
    
    /**
     * Creates the Feedback content panel
     */
    private void createFeedbackPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        
        // Create Feedback header
        JLabel feedbackHeaderLabel = new JLabel("Feedback");
        feedbackHeaderLabel.setFont(SUBHEADER_FONT);
        feedbackHeaderLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create Feedback form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Feedback Type
        JLabel typeLabel = new JLabel("Feedback Type");
        typeLabel.setFont(CONTENT_FONT);
        
        String[] feedbackTypes = {"Select feedback type", "Bug Report", "Feature Request", "General Feedback", "Other"};
        JComboBox<String> typeComboBox = new JComboBox<>(feedbackTypes);
        typeComboBox.setFont(CONTENT_FONT);
        
        // Subject
        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setFont(CONTENT_FONT);
        
        JTextField subjectField = new JTextField();
        subjectField.setFont(CONTENT_FONT);
        
        // Message
        JLabel messageLabel = new JLabel("Message");
        messageLabel.setFont(CONTENT_FONT);
        
        JTextArea messageArea = new JTextArea();
        messageArea.setFont(CONTENT_FONT);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setRows(8);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        
        // Contact Info
        JLabel contactLabel = new JLabel("Contact Info");
        contactLabel.setFont(CONTENT_FONT);
        
        JTextField contactField = new JTextField();
        contactField.setFont(CONTENT_FONT);
        
        // Submit Button
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.setBackground(PRIMARY_BLUE);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(CONTENT_FONT);
        submitButton.setFocusPainted(false);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        
        // Add components to form
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        formPanel.add(typeLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        formPanel.add(typeComboBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        formPanel.add(subjectLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        formPanel.add(subjectField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        formPanel.add(messageLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        formPanel.add(messageScrollPane, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.2;
        formPanel.add(contactLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        formPanel.add(contactField, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(submitButton, gbc);
        
        // Add to main panel
        mainPanel.add(feedbackHeaderLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        contentPanel.add(mainPanel, "Feedback");
    }
    
    /**
     * Adds a FAQ item to the FAQ panel
     */
    private void addFAQItem(String question) {
        JPanel faqItemPanel = new JPanel();
        faqItemPanel.setLayout(new BorderLayout());
        faqItemPanel.setBackground(Color.WHITE);
        faqItemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 0, 15, 0)
        ));
        faqItemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        JLabel questionLabel = new JLabel(question);
        questionLabel.setFont(CONTENT_FONT);
        
        JLabel arrowLabel = new JLabel("›");
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 16));
        arrowLabel.setForeground(DARK_GRAY);
        
        faqItemPanel.add(questionLabel, BorderLayout.WEST);
        faqItemPanel.add(arrowLabel, BorderLayout.EAST);
        
        // Make the panel clickable
        faqItemPanel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                faqItemPanel.setBackground(LIGHT_GRAY);
            }
            
            public void mouseExited(MouseEvent evt) {
                faqItemPanel.setBackground(Color.WHITE);
            }
            
            public void mouseClicked(MouseEvent evt) {
                // Here you would handle showing the answer to the question
                JOptionPane.showMessageDialog(null, "This would show the answer to: " + question);
            }
        });
        
        faqPanel.add(faqItemPanel);
    }
    
    /**
     * Main method to test the HelpCenterView
     */
    public static void main(String[] args) {
        // Set the look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Run the application
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HelpCenterView();
            }
        });
    }
} 