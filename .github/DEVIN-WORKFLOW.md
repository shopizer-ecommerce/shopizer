# Devin Workflow for Java 17 Migration

## How to Assign Work to Devin

Use GitHub labels to signal which issues you want Devin to work on:

### Labels

- **`ready`** - Add this label to issues you want Devin to start working on
- **`in-progress`** - Devin will add this when actively working on an issue
- **`blocked`** - Add this if an issue cannot be started due to dependencies
- **`phase1` through `phase7`** - Phase organization labels
- **`high-priority`**, **`critical`**, **`medium-priority`** - Priority levels

### Workflow

1. **Review the issues** in your GitHub repository: https://github.com/haoyxc/shopizer/issues
2. **Add the `ready` label** to the next issue you want Devin to work on
3. **Tell Devin** which issue to work on by saying:
   - "Start working on issue #X"
   - "Work on the ready issues"
   - "Continue with the next task in phase X"

### Example Commands

```
"Start working on issue #3 (Task 1.1: Dependency Compatibility Analysis)"
"Work on all ready issues in phase 1"
"Continue with the next high-priority task"
"What issues are marked as ready?"
```

### Current Status

- **Epic:** #1
- **Current Branch:** `feature/java17-migration`
- **Completed:** Task 1.2 (branch creation)
- **Next Step:** Add `ready` label to Task 1.1 (#3) to begin dependency analysis

### Automatic Progress

Devin will:
1. Add `in-progress` label when starting work
2. Remove `in-progress` and add appropriate labels when complete
3. Update issue comments with progress
4. Commit changes to the feature branch
5. Reference the issue number in commit messages

### Issue Hierarchy

```
Epic #1
├── Phase 1 #2
│   ├── Task 1.1 #3
│   ├── Task 1.2 #4 ✅ (completed)
│   └── Task 1.3 #5
├── Phase 2 #6
│   ├── Task 2.1 #7
│   ├── Task 2.2 #8
│   ├── Task 2.3 #9
│   └── Task 2.4 #10
└── ... (remaining phases)
```

## Quick Start

To begin the migration, run:

```bash
gh issue edit 3 --add-label ready
```

Then tell me: "Start working on issue #3"
