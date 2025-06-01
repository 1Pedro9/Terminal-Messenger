# Terminal-Messenger

## Idea
Many Linux users are programmers. Programmers and especially Linux users who program love the terminal, because it looks so cool to operate, yet it is so simple to use. The terminal messenger has the same idea where it is a very simplex program that looks like a terminal but acts as a messenger. 

## Structure
The structure of the program is devided into 4 main packages.
1. Controller
2. Main
3. Model
4. View

### Controller
The controller package is as it describes. It is what controlls things like the mouse & key events, reading from files, managing database, etc.

Thus far the package consist of the following classes
1. Controller
```java

    public Controller(): Constructor
    public getContacts(): ArrayList<Contact>
    public getMessages(): ArrayList<Message>
    public getContact(): Contact
    public addMessage(message: Message): void

```

2. KeyHandler
```java

    private final chat: Chat

    public KeyHandler(chat: Chat): Constructor
    public keyPressed(keyEvent: KeyEvent): void

```

3. MouseHandler
```java
    // Not yet implimented
```

4. WebHandler
```java

    private final link: String

    public sendMessage(send_id: int, receive_id: int, message: String): String
    public getMessage(): void       // Not yet implimented

```

### Main
The main package is only there for main files such as the main file where the `public static void main()` is. 

1. Main     Extends: **JFrame**,    Impliments: **Runnable**
```java

    private WIDTH: int
    private HEIGHT: int
    private thread: Thread
    private scroll: JScrollPane
    private chat: Chat
    private keyHandler: KeyHandler

    private keyEvents(): void
    private render(): void
    private update(): void
    private synchronized start(): void
    private synchronized stop(): void
    

    public static running: boolean
    public STATE: enum
    public state: STATE
    public static me: Contact

    public Main(): Constructor
    public init(): void
    public run(): void
    public static main(args: String[]): void

```

### Model
The model package is where all the objects are that are needed for the project. This is where we build our own data types to use.

1. Contact
```java

    private id: int
    private username: String
    private email: String
    private number: String

    public Contact(id: int, username: String, email: String, number: String): Constructor
    public toString(): String
    public getUsername(): String
    public getID(): int

```

2. Message
```java

    private id: int
    private message: String
    private contact: Contact
    
    public currentUser: boolean

    public Message(id: int, contact: Contact, message: String): Constructor
    public toString(): String
    public line(): String

```

### View
This package has all the gui elements. Each class is represented as a page.

1. Chat     extends: **JPanel**
```java

    private text: String
    private cursorShown: boolean
    private cursorFreq: int
    private controller: Controller
    private webHandler: WebHandler
    
    private init(): void

    public inputString: String
    public cursorPos: int
    public messages: ArrayList<Message>

    public Chat(): Constructor
    public render(): void
    public append(s: char): void
    public createMessage(): void

```