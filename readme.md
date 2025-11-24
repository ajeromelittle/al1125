# Rental Controller API

## PORT 8080
## Endpoint

**POST** `/api/rentItems/`

Calculates rental charges for tool rentals based on rental terms.

## Request Body

```json
{
  "toolCode": "LADW",
  "checkoutDate": "07/02/20",
  "rentalDayCount": 3,
  "discountPercent": 10
}
```

**Fields:**
- `toolCode` (required): Tool code enum (CHNS, LADW, JAKD, JAKR)
- `checkoutDate` (required): Checkout date in MM/dd/yy format
- `rentalDayCount` (required): Number of rental days (min: 1)
- `discountPercent`: Discount percentage (0-100)

## Response

```json
{
  "toolCode": "LADW",
  "toolType": "LADDER",
  "toolBrand": "WERNER",
  "rentalDays": 3,
  "checkoutDate": "2020-07-02",
  "dueDate": "2020-07-05",
  "dailyCharge": 1.99,
  "chargeableDays": 2,
  "subTotal": 3.98,
  "discountPercent": 10,
  "discountAmount": 0.40,
  "discountCharge": 3.58
}
```
