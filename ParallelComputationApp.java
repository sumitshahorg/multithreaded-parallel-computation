import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Parallel Computation Application
 * Demonstrates multithreading in Java using parallel matrix multiplication
 * 
 * @author Sumit Shah , Suraj Rawat
 * @version 1.0
 */
public class ParallelComputationApp extends JFrame {
    
    // GUI Components
    private JTextField matrixSizeField;
    private JTextField threadCountField;
    private JTextArea outputArea;
    private JProgressBar progressBar;
    private JButton startButton;
    private JButton clearButton;
    private JLabel statusLabel;
    private JRadioButton singleThreadRadio;
    private JRadioButton multiThreadRadio;
    
    // Computation variables
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] resultMatrix;
    private int matrixSize;
    private int threadCount;
    private long executionTime;
    
    /**
     * Constructor - Initialize GUI
     */
    public ParallelComputationApp() {
        super("Parallel Computation - Matrix Multiplication");
        initializeGUI();
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * Initialize all GUI components using AWT and Swing
     */
    private void initializeGUI() {
        // Set Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Title Panel
        JPanel titlePanel = createTitlePanel();
        
        // Input Panel
        JPanel inputPanel = createInputPanel();
        
        // Output Panel
        JPanel outputPanel = createOutputPanel();
        
        // Control Panel
        JPanel controlPanel = createControlPanel();
        
        // Status Panel
        JPanel statusPanel = createStatusPanel();
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        bottomPanel.add(statusPanel, BorderLayout.SOUTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Create title panel with project information
     */
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(51, 122, 183));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Multithreaded Parallel Computation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);
        
        return panel;
    }
    
    /**
     * Create input panel for user parameters
     */
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            "Computation Parameters",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Matrix Size
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Matrix Size (N x N):"), gbc);
        
        gbc.gridx = 1;
        matrixSizeField = new JTextField("100", 15);
        panel.add(matrixSizeField, gbc);
        
        // Thread Count
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Number of Threads:"), gbc);
        
        gbc.gridx = 1;
        threadCountField = new JTextField("4", 15);
        panel.add(threadCountField, gbc);
        
        // Computation Mode
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Computation Mode:"), gbc);
        
        gbc.gridx = 1;
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup modeGroup = new ButtonGroup();
        
        singleThreadRadio = new JRadioButton("Single Thread");
        multiThreadRadio = new JRadioButton("Multi Thread", true);
        
        modeGroup.add(singleThreadRadio);
        modeGroup.add(multiThreadRadio);
        radioPanel.add(singleThreadRadio);
        radioPanel.add(multiThreadRadio);
        panel.add(radioPanel, gbc);
        
        return panel;
    }
    
    /**
     * Create output panel for displaying results
     */
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            "Computation Results",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14)
        ));
        
        outputArea = new JTextArea(15, 60);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(240, 240, 240));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Create control panel with buttons
     */
    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        startButton = new JButton("Start Computation");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBackground(new Color(92, 184, 92));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.setPreferredSize(new Dimension(180, 35));
        
        clearButton = new JButton("Clear Output");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setBackground(new Color(240, 173, 78));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.setOpaque(true);
        clearButton.setBorderPainted(false);
        clearButton.setPreferredSize(new Dimension(150, 35));
        
        // Add action listeners
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startComputation();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                progressBar.setValue(0);
                statusLabel.setText("Ready");
            }
        });
        
        panel.add(startButton);
        panel.add(clearButton);
        
        return panel;
    }
    
    /**
     * Create status panel with progress bar
     */
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBorder(new EmptyBorder(5, 0, 0, 0));
        
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(0, 25));
        
        panel.add(statusLabel, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Start the parallel computation process
     */
    private void startComputation() {
        try {
            // Validate inputs
            matrixSize = Integer.parseInt(matrixSizeField.getText().trim());
            threadCount = Integer.parseInt(threadCountField.getText().trim());
            
            if (matrixSize <= 0 || matrixSize > 1000) {
                JOptionPane.showMessageDialog(this,
                    "Matrix size must be between 1 and 1000",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (threadCount <= 0 || threadCount > 100) {
                JOptionPane.showMessageDialog(this,
                    "Thread count must be between 1 and 100",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Disable buttons during computation
            startButton.setEnabled(false);
            outputArea.setText("");
            progressBar.setValue(0);
            
            // Create worker thread for computation
            SwingWorker<Void, String> worker = new ComputationWorker();
            worker.execute();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Please enter valid numeric values",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * SwingWorker class to perform computation in background
     */
    class ComputationWorker extends SwingWorker<Void, String> {
        
        @Override
        protected Void doInBackground() throws Exception {
            publish("=".repeat(70) + "\n");
            publish("PARALLEL COMPUTATION STARTED\n");
            publish("=".repeat(70) + "\n\n");
            
            // Step 1: Generate matrices
            publish("Step 1: Generating random matrices...\n");
            statusLabel.setText("Generating matrices...");
            progressBar.setValue(10);
            generateMatrices();
            publish("✓ Generated two " + matrixSize + "x" + matrixSize + " matrices\n\n");
            
            // Step 2: Display matrix info
            publish("Matrix A (sample 3x3 corner):\n");
            displayMatrixSample(matrixA);
            publish("\nMatrix B (sample 3x3 corner):\n");
            displayMatrixSample(matrixB);
            publish("\n");
            
            progressBar.setValue(25);
            
            // Step 3: Perform computation based on mode
            if (multiThreadRadio.isSelected()) {
                performMultiThreadedComputation();
            } else {
                performSingleThreadedComputation();
            }
            
            // Step 4: Display results
            progressBar.setValue(90);
            publish("\nResult Matrix (sample 3x3 corner):\n");
            displayMatrixSample(resultMatrix);
            
            publish("\n" + "=".repeat(70) + "\n");
            publish("COMPUTATION COMPLETED SUCCESSFULLY\n");
            publish("Execution Time: " + executionTime + " ms\n");
            publish("=".repeat(70) + "\n");
            
            progressBar.setValue(100);
            statusLabel.setText("Computation completed in " + executionTime + " ms");
            
            return null;
        }
        
        @Override
        protected void process(java.util.List<String> chunks) {
            for (String text : chunks) {
                outputArea.append(text);
            }
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        }
        
        @Override
        protected void done() {
            startButton.setEnabled(true);
        }
        
        /**
         * Perform single-threaded matrix multiplication
         */
        private void performSingleThreadedComputation() throws Exception {
            publish("Step 3: Performing SINGLE-THREADED computation...\n");
            statusLabel.setText("Computing (Single Thread)...");
            progressBar.setValue(40);
            
            long startTime = System.currentTimeMillis();
            
            resultMatrix = new int[matrixSize][matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    resultMatrix[i][j] = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
                
                // Update progress
                if (i % (matrixSize / 10) == 0) {
                    int progress = 40 + (int) ((i * 45.0) / matrixSize);
                    progressBar.setValue(progress);
                }
            }
            
            executionTime = System.currentTimeMillis() - startTime;
            
            publish("✓ Single-threaded computation completed\n");
            publish("  Time taken: " + executionTime + " ms\n");
        }
        
        /**
         * Perform multi-threaded matrix multiplication using thread pool
         */
        private void performMultiThreadedComputation() throws Exception {
            publish("Step 3: Performing MULTI-THREADED computation...\n");
            publish("  Using " + threadCount + " parallel threads\n");
            statusLabel.setText("Computing (Multi-threaded)...");
            progressBar.setValue(40);
            
            long startTime = System.currentTimeMillis();
            
            resultMatrix = new int[matrixSize][matrixSize];
            
            // Create thread pool
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(matrixSize);
            
            // Divide work among threads (row-wise distribution)
            for (int i = 0; i < matrixSize; i++) {
                final int row = i;
                executor.submit(new MatrixMultiplicationTask(row, latch));
                
                // Update progress
                if (i % (matrixSize / 10) == 0) {
                    int progress = 40 + (int) ((i * 45.0) / matrixSize);
                    progressBar.setValue(progress);
                }
            }
            
            // Wait for all threads to complete
            latch.await();
            executor.shutdown();
            
            executionTime = System.currentTimeMillis() - startTime;
            
            publish("✓ Multi-threaded computation completed\n");
            publish("  Threads used: " + threadCount + "\n");
            publish("  Time taken: " + executionTime + " ms\n");
        }
        
        /**
         * Display sample 3x3 corner of matrix
         */
        private void displayMatrixSample(int[][] matrix) {
            int displaySize = Math.min(3, matrixSize);
            for (int i = 0; i < displaySize; i++) {
                publish("  ");
                for (int j = 0; j < displaySize; j++) {
                    publish(String.format("%6d ", matrix[i][j]));
                }
                if (matrixSize > 3) publish("...");
                publish("\n");
            }
            if (matrixSize > 3) {
                publish("  ...\n");
            }
        }
    }
    
    /**
     * Runnable task for computing one row of result matrix
     */
    class MatrixMultiplicationTask implements Runnable {
        private int row;
        private CountDownLatch latch;
        
        public MatrixMultiplicationTask(int row, CountDownLatch latch) {
            this.row = row;
            this.latch = latch;
        }
        
        @Override
        public void run() {
            try {
                // Compute one row of the result matrix
                for (int j = 0; j < matrixSize; j++) {
                    resultMatrix[row][j] = 0;
                    for (int k = 0; k < matrixSize; k++) {
                        resultMatrix[row][j] += matrixA[row][k] * matrixB[k][j];
                    }
                }
            } finally {
                latch.countDown();
            }
        }
    }
    
    /**
     * Generate random matrices for computation
     */
    private void generateMatrices() {
        Random random = new Random();
        matrixA = new int[matrixSize][matrixSize];
        matrixB = new int[matrixSize][matrixSize];
        
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrixA[i][j] = random.nextInt(10);
                matrixB[i][j] = random.nextInt(10);
            }
        }
    }
    
    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ParallelComputationApp app = new ParallelComputationApp();
                app.setVisible(true);
            }
        });
    }
}
