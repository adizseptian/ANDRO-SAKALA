# 15 — BUILD, RELEASE & DELIVERY PLAN

## Tahap A — Foundation
- Gradle project, dependency locking, package structure.
- Theme/tokens, navigation, Room baseline.
- CI compile/lint/test.
- No INTERNET permission.

## Tahap B — Domain correctness
- Inventory pool and Moving Average with exact remainder policy.
- Sales/discount, expense, non-cash, other income, deposit.
- Reconciliation engine and status matrix.
- Golden tests.

## Tahap C — Operational screens
- Product, package, opening stock, purchase, physical count.
- Sales and all event forms.
- Expense and other income.
- Deposit and reconciliation.

## Tahap D — Period governance
- Period preview, close, immutable snapshot.
- Linked correction and void constraints.
- Audit history.

## Tahap E — Reports
- Weekly statements, profit/loss, stock movement, deposits.
- PDF/CSV export.

## Tahap F — Backup and restore
- SAF, AES-GCM container, passphrase KDF.
- Staging restore and atomic swap.
- Restore drills and failure injection.

## Tahap G — Hardening
- Accessibility, performance, migration, crash recovery.
- Device matrix and regression.
- Privacy review and release checklist.

## Release gate
Release APK/AAB only after:
- All P0/P1 acceptance criteria pass.
- Domain golden tests pass.
- Backup/restore drills pass.
- No unresolved critical data-integrity defects.
- Owner approves all unresolved business decisions.
- Source, version, and release notes are tagged.
