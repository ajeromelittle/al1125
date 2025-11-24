# My Experience Using AI for the Tool Rental Project

**Developer:** AJ  
**Project:** Tool Rental System (Java/Spring Boot)  
**AI Tool:** Claude  

---

## Overview

I used Claude to help me build a Java Tool Rental System with Spring Boot. Honestly, it was like having a really knowledgeable senior dev available 24/7. The AI helped me work through some complex business logic, figure out the best way to implement things, and debug issues way faster than I could have on my own. It wasn't perfect - I had to learn how to ask the right questions - but overall it saved me a ton of time.

---

## How I Actually Used It

### Quick Research
When I needed to check for holidays and weekends in Java, instead of spending hours on Stack Overflow, I just asked Claude: "Is there an API for this?" It immediately pointed me to Jollyday, which ended up being perfect for what I needed.

### Debugging
I hit an error with Jollyday not working. I just sent a screenshot of the error and Claude told me exactly what dependency I was missing. Saved me probably an hour of debugging.

### Learning Patterns
I forgot how to properly implement the Builder pattern. Instead of reading through tutorials, I asked Claude and it gave me examples using my actual rental system code. Way more useful than generic examples.

### Syntax Help
Couldn't remember the exact syntax for `@Valid` and `@Validated` in Spring Boot. Asked Claude and got a complete example with exception handling. Much faster than digging through old projects.

### Test Cases
I gave Claude the business requirements and asked for help with test cases. It generated way more edge cases than I would have thought of on my own and organized everything into utility classes.

### Refactoring
Every time I realized I misunderstood a requirement (like what "chargeable days" actually meant), Claude helped me refactor all the affected code consistently across the project.

---

## What Worked Really Well

**Context Memory:** Claude remembered everything from earlier in our conversation. When I asked about validation later, it used rental-specific examples without me having to explain the project again.

**Complete Examples:** I got full working code with imports and error handling, not just fragments. I could literally copy-paste and run it.

**Best Practices Built In:** Claude automatically used `BigDecimal` for currency (and explained why), set up proper exception handlers, and suggested comprehensive test coverage without me asking.

**Multiple Options:** When I asked about validation, Claude didn't just give me one answer - it explained the different approaches and when to use each one.

**Easy to Refactor:** When I corrected something (like the holiday observation rules), Claude updated all the relevant code immediately without getting confused.

---

## Challenges I Ran Into

### Being Too Vague
**Problem:** Early on, I wasn't clear about what "charge days" versus "rental days" meant. Claude generated test cases with wrong expected values.

**Fix:** I had to spell it out: "The charge days are ONLY the days where a customer can be charged for the tool."

**Lesson:** Be really specific with domain terms from the start.

### Code Structure Mismatches
**Problem:** Claude assumed my methods were named like `getDailyRentalCharge()` but I actually used `getDailyCharge()`.

**Fix:** I sent screenshots of my actual classes and told it to use my naming.

**Lesson:** Show your actual code early so names and structures match up.

### Getting Too Much
**Problem:** Claude generated a bunch of validation tests I didn't need because those were being handled in the controller.

**Fix:** I clarified: "Let's not test any validation exceptions because they will be handled in the controller test."

**Lesson:** Tell AI what you DON'T want, not just what you do want.

### Business Logic Assumptions
**Problem:** The rule about "first chargeable day is the day AFTER checkout" wasn't clear initially, causing off-by-one errors.

**Fix:** I explicitly stated: "The first charge day starts on the day AFTER checkout. Not the day of checkout."

**Lesson:** Don't assume AI will infer complex business rules. State them explicitly.

---

## Examples Where I Had to Refine Prompts

**Test Organization**
- First: "I need to write test cases" → Too vague
- Better: "I need test cases. The 6 mandatory ones are shown, plus more edge cases. Use my RentalTermPayloads utility class for all test data."

**Using Enums**
- First: "Update method names" → Fixed methods but still used strings
- Better: "Update method names AND use enum values instead of strings like 'Ladder'"

**Complete Refactoring**
- First: "Update test cases" → Only updated tests, not payloads
- Better: "Refactor BOTH test class AND util class. Move all rental terms to RentalTermPayloads. Don't test validation exceptions."

**Business Logic**
- First: Sent requirements doc → AI confused rental days with chargeable days
- Better: "Charge days are ONLY billable days. Example: 3 rental days with 1 holiday = 2 charge days."

---

## What I Learned About Prompting

**Show, Don't Tell:** Screenshots of my actual code worked way better than describing it.

**Use Concrete Examples:** "3 rental days with 1 holiday = 2 charge days" was clearer than abstract explanations.

**Break It Down:** Split big requests into smaller pieces instead of asking for everything at once.

**Be Explicit:** Say what you don't want, not just what you want.

**Build on Context:** Reference earlier conversations instead of starting over.

---

## Time Impact

Claude saved me about **9-10 hours** on:
- Library research (~2 hours)
- Debugging issues (~1 hour)
- Writing test cases (~3 hours)
- Looking up syntax (~1 hour)
- Refactoring (~2 hours)

Quality was better too - more edge cases covered, better patterns used, consistent code style.

---

## When AI Wasn't Helpful

**Domain Logic:** Claude couldn't figure out business rules without me explaining them explicitly.

**Runtime Bugs:** Great for compilation errors, but I still had to test everything myself.

**Architecture:** AI helped with implementation but I made the structural decisions.

---

## Bottom Line

Using Claude for this project was awesome. It was like pair programming with someone who's always available, knows a ton, and generates code super fast. The key was learning how to ask good questions - being specific, providing context, and iterating when the first attempt wasn't quite right.

The best part wasn't even the code generation - it was learning *why* certain approaches are better. Claude explained things like why to use `BigDecimal` for currency, how to structure tests properly, and what best practices to follow.

That said, AI didn't replace me as a developer. I still needed to understand the requirements, make architectural decisions, verify the business logic, and test everything. It worked best as a collaboration - I provided the requirements and context, Claude provided implementation and best practices.

The time savings (~9-10 hours) and quality improvements made it incredibly valuable for this project.

---

**Project Stats:**
- Time spent: ~8 hours of development
- AI conversations: 16 major interactions
- Language: Java 17
- Framework: Spring Boot 3.2+
- Key libraries: Jollyday, Bean Validation, JUnit 5
