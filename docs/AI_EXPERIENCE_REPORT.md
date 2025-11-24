# My Experience Using AI for the Tool Rental Project

**AI Tool:** Claude  

---

## Overview

I used Claude to help me build this Java Tool Rental System with Spring Boot. The AI helped me work through some complex business logic, figure out the best way to implement things, and debug issues way faster than I could have on my own. 
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
I gave Claude the business requirements and asked for help with test cases. It generated way more edge cases than I would have thought of on my own and organized everything into utility classes when asked

### Refactoring
Every time I realized AI misunderstood a requirement (like what "chargeable days" actually meant), Claude helped me refactor all the affected code consistently across the project.

---

## What Worked Really Well

**Complete Examples:** I got full working code with imports and error handling, not just fragments. I could literally copy-paste and run it, especially for test cases.

**Multiple Options:** When I asked about validation, Claude didn't just give me one answer - it explained the different approaches and when to use each one.

**Easy to Refactor:** When I corrected something (like the holiday observation rules), Claude updated all the relevant code immediately without getting confused.

---

## Challenges I Ran Into

### Being Too Vague / Expecting Claude to Always Remeber Previous Information
**Problem:** Early on, I wasn't clear about what "charge days" versus "rental days" meant. Claude generated test cases with wrong expected values.

**Fix:** I had to spell it out: "The charge days are ONLY the days where a customer can be charged for the tool."

### Code Structure Mismatches
**Problem:** Claude assumed my methods were named like `getDailyRentalCharge()` but I actually used `getDailyCharge()`.

**Fix:** I sent screenshots of my actual classes and told it to use my naming.

### Getting Too Much (Most common issue)
**Problem:** Claude generated a bunch of validation tests I didn't need because those were being handled in the controller. In general Claude AI gives alot of information

**Fix:** I clarified: "Let's not test any validation exceptions because they will be handled in the controller test." I also made sure to sift through all responses to find 
core answer to my question.

### Business Logic Assumptions
**Problem:** The rule about "first chargeable day is the day AFTER checkout" wasn't clear initially, causing off-by-one errors.

**Fix:** I explicitly stated: "The first charge day starts on the day AFTER checkout. Not the day of checkout."

---

## Examples Where I Had to Refine Prompts

**Test Organization**
- First: "I need to write test cases" → Too vague
- Better: "I need test cases. The 6 mandatory ones are shown, plus more edge cases. Use my RentalTermPayloads utility class for all test data."

**Using Enums**
- First: "Update method names in test classes" → Fixed methods but still used strings
- Better: "Update method names AND use enum values instead of strings like 'Ladder'"

**Complete Refactoring**
- First: "Update test cases" → Only updated tests, not payloads
- Better: "Refactor BOTH test class AND util class. Move all rental terms to RentalTermPayloads. Don't test validation exceptions."

**Business Logic**
- First: Sent requirements doc → AI confused rental days with chargeable days
- Better: "Charge days are ONLY billable days. Example: 3 rental days with 1 holiday = 2 charge days."

---

## Time Impact

Claude saved me about **6 hours** on:
- Library research (~1 hours)
- Debugging issues (~1 hour)
- Creating / Writing test cases (~3-4 hours)
- Looking up syntax (~1 hour)
- Refactoring (~1 hours)

---

## When AI Wasn't Helpful

**Domain Logic:** Claude couldn't figure out business rules without me explaining them explicitly.

**Runtime Bugs:** Great for compilation errors, but I still had to test everything myself.

**Architecture:** AI helped with implementation but I made the structural decisions.

