# Priority Queue ADT using Heap ✈️

This project implements a **Priority Queue Abstract Data Type (ADT)** using a **heap** and demonstrates its functionality through a simple simulation of an **airline standby list**.

## 🛠️ Project Features
1. **Heap Implementation**:
   - Utilizes a dynamic `ArrayList` as the underlying data structure.
   - Includes a default comparator for custom object comparison.
2. **Passenger Priority**:
   - Passengers are prioritized based on fare code, flyer status, and timestamp in the following order:
     - **Fare Code**: Full > Discount (Disc) > Buddy.
     - **Flyer Status**: Gold > Silver > Bronze > None.
     - **Timestamp**: Earlier passengers are given higher priority if all other attributes are equal.
3. **Driver Simulation**:
   - Adds and removes passengers from the standby list based on priority.
   - Handles dynamic operations such as adding/removing passengers and reordering the queue.

### Clone this repository:
git clone https://github.com/Ma-n-a-v/Airplane_standby_using_priority_queue.git

## 📝 Classes and Key Components
### HeapPriorityQueue Implementation
The HeapPriorityQueue class is the backbone of the priority queue. 
It extends the abstract class AbstractPriorityQueue and uses a heap (implemented via a custom ArrayList) to store and prioritize elements. 
Below is an explanation of its core components:

### Class Design
The HeapPriorityQueue class:
Implements: The PriorityQueue and Entry interfaces to define priority queue behavior.
Extends: AbstractPriorityQueue to inherit reusable methods and properties for maintaining the queue.

### Core Components
a) Heap Representation
The heap is implemented using a custom ArrayList:
ArrayList: Serves as a dynamic array for storing heap elements.
Binary Heap Structure: Ensures the priority order is maintained, with the highest-priority element at the root (index 0).
b) Comparator for Priority
Custom Comparator: Allows prioritization of passengers based on:
Fare Code: Full > Disc > Buddy
Flyer Status: Gold > Silver > Bronze > None
Timestamp: Earlier timestamps have higher priority when other attributes are equal.
If no custom comparator is provided, a default comparator based on passport numbers is used.

### Advantages
Efficiency: Both insertion and deletion operate in 
O(log n)
O(log n) due to the binary heap.
Flexibility: Custom comparator makes it adaptable for other use cases.
Dynamic Behavior: Uses a custom dynamic ArrayList to avoid size constraints.
