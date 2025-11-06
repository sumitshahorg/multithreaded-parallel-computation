# Multithreaded Parallel Computation System

A Java desktop application demonstrating parallel computing through multithreaded matrix multiplication with an interactive GUI.

## 📋 Project Overview

This project implements a **Parallel Matrix Multiplication** algorithm using Java multithreading concepts. It provides a graphical user interface built with Swing/AWT that allows users to:
- Compare single-threaded vs multi-threaded performance
- Configure matrix size and thread count
- Visualize computation progress in real-time
- View execution time metrics

## 🎯 Key Features

- ✅ **Multithreading**: Uses ExecutorService and thread pools
- ✅ **GUI**: Full-featured Swing/AWT interface
- ✅ **Performance Comparison**: Single vs multi-threaded modes
- ✅ **Real-time Updates**: Progress bar and status indicators
- ✅ **Input Validation**: Error handling and user-friendly messages
- ✅ **OOP Design**: Well-structured, maintainable code

## 🛠️ Technologies Used

- Java (JDK 8+)
- Swing & AWT (GUI components)
- Multithreading (ExecutorService, CountDownLatch, SwingWorker)
- Object-Oriented Programming

## 📦 Requirements

- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **RAM**: Minimum 2GB (4GB+ recommended)
- **Processor**: Dual-core or better (Quad-core recommended)

## 🚀 Quick Start

### 1. Compile the Program

```bash
javac ParallelComputationApp.java
```

### 2. Run the Application

```bash
java ParallelComputationApp
```

### 3. Use the Application

1. **Enter Matrix Size**: Input the size of square matrices (e.g., 100, 200, 500)
2. **Enter Thread Count**: Specify number of threads (e.g., 4, 8)
3. **Select Mode**: Choose between "Single Thread" or "Multi Thread"
4. **Start Computation**: Click the green "Start Computation" button
5. **View Results**: Observe the execution time and matrix results

## 📸 Sample Usage

```
Matrix Size: 200
Thread Count: 4
Mode: Multi Thread

Expected Output:
- Matrix generation confirmation
- Sample matrix values (3x3 corner)
- Computation progress
- Execution time (significantly faster with multiple threads)
- Result matrix sample
```

## 🔍 Testing Recommendations

### Small Matrix (Fast execution)
- Matrix Size: 50
- Thread Count: 2
- Good for quick testing

### Medium Matrix (Visible speedup)
- Matrix Size: 200-300
- Thread Count: 4
- Best for demonstrating performance improvement

### Large Matrix (Maximum speedup)
- Matrix Size: 500-800
- Thread Count: 8
- Shows significant performance gains

## 📊 Performance Expectations

| Matrix Size | Single Thread | Multi Thread (4 cores) | Speedup |
|-------------|---------------|------------------------|---------|
| 100×100     | ~50 ms        | ~20 ms                 | 2.5x    |
| 200×200     | ~400 ms       | ~150 ms                | 2.7x    |
| 500×500     | ~6000 ms      | ~2000 ms               | 3.0x    |

*Note: Actual times vary based on hardware specifications*

## 📁 Project Structure

```
java_project/
│
├── ParallelComputationApp.java    # Main application source code
├── PROJECT_DOCUMENTATION.md       # Complete project documentation
└── README.md                      # This file
```

## 🎓 Concepts Demonstrated

1. **Multithreading**
   - Thread creation and management
   - Thread pools (ExecutorService)
   - Thread synchronization (CountDownLatch)
   - SwingWorker for background tasks

2. **GUI Programming**
   - JFrame, JPanel, JButton, JTextField
   - Layout managers (BorderLayout, GridBagLayout)
   - Event handling (ActionListener)
   - Progress tracking (JProgressBar)

3. **OOP Principles**
   - Encapsulation
   - Inheritance
   - Inner classes
   - Interface implementation

4. **Algorithm Design**
   - Matrix multiplication
   - Parallel task distribution
   - Load balancing

## 🐛 Troubleshooting

### Issue: "javac: command not found"
**Solution**: Install JDK and add it to your PATH
- Windows: Set JAVA_HOME environment variable
- macOS: `brew install openjdk`
- Linux: `sudo apt install default-jdk`

### Issue: GUI not appearing
**Solution**: Ensure you're using JDK (not just JRE) and try:
```bash
java -version  # Should show version 8 or higher
```

### Issue: Out of memory for large matrices
**Solution**: Increase heap size:
```bash
java -Xmx2g ParallelComputationApp
```

### Issue: Slow performance
**Solution**: 
- Use thread count equal to CPU cores
- Don't exceed available system resources
- Close other resource-intensive applications

## 📝 Documentation

Complete project documentation with all sections required for submission is available in:
- **PROJECT_DOCUMENTATION.md**

This includes:
1. Cover Page
2. Certificate
3. Acknowledgement
4. Introduction
5. Objective/Problem Statement
6. Methodology/Implementation
7. Program Code
8. Output Screenshots Guide
9. Conclusion
10. References

## 💡 Tips for Project Submission

1. **Screenshots**: Run the application and capture:
   - Initial GUI
   - Multi-threaded execution results
   - Single-threaded comparison
   - Error handling demonstration

2. **Customization**: Update PROJECT_DOCUMENTATION.md with:
   - Your name and roll number
   - Your institute details
   - Your guide's name
   - Actual screenshots from your execution

3. **Presentation**: Print or PDF the documentation in this order:
   - Cover page → Certificate → Acknowledgement → Introduction → 
   - Objective → Methodology → Code → Screenshots → Conclusion → References

## 📚 Learning Resources

- Oracle Java Tutorials: https://docs.oracle.com/javase/tutorial/
- Java Concurrency: https://docs.oracle.com/javase/tutorial/essential/concurrency/
- Swing Tutorial: https://docs.oracle.com/javase/tutorial/uiswing/

## 👨‍💻 Author

**[Your Name]**  
Roll No: [Your Roll Number]  
Department: [Your Department]  
Institute: [Your Institute Name]

## 📄 License

This is an educational project created for academic purposes.

## 🤝 Acknowledgments

- Course Instructor: [Guide Name]
- Department: [Department Name]
- Institute: [Institute Name]

---

**For any issues or questions, please refer to the PROJECT_DOCUMENTATION.md file or consult your project guide.**

**Good luck with your project submission! 🎓**
