# Optimistic Locking Test

Includes 3 test cases.

| TestCase | expected | result |
|----|----|----|
| testActiveByJpaRepository | not throw optimistic locking exception  | throw optimistic locking exception |
| testActiveByEntityManager | not throw | throw |
| testActiveByRefreshEntity | not throw | not throw |