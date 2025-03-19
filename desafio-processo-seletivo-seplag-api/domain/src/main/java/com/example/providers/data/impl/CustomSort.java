package com.example.providers.data.impl;

import java.util.*;
import java.util.stream.Collectors;

public class CustomSort {

    private static final CustomSort UNSORTED = by();
    public static final CustomDirection DEFAULT_DIRECTION;
    private final List<CustomOrder> orders;

    protected CustomSort(List<CustomOrder> orders) {
        this.orders = orders;
    }

    private CustomSort(CustomDirection direction, List<String> properties) {
        if (properties != null && !properties.isEmpty()) {
            this.orders = properties.stream().map((it) -> new CustomOrder(direction, it)).collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("You have to provide at least one property to sort by");
        }
    }

    public static CustomSort by() {
        return new CustomSort(new ArrayList<>());
    }

    public static CustomSort by(String... properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Properties must not be null");
        }
        return properties.length == 0 ? unsorted() : new CustomSort(DEFAULT_DIRECTION, Arrays.asList(properties));
    }

    public static CustomSort by(List<CustomOrder> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("Orders must not be null");
        }
        return orders.isEmpty() ? unsorted() : new CustomSort(orders);
    }

    public static CustomSort by(CustomOrder... orders) {
        if (orders == null) {
            throw new IllegalArgumentException("Orders must not be null");
        }
        return new CustomSort(Arrays.asList(orders));
    }

    public static CustomSort by(CustomDirection direction, String... properties) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction must not be null");
        }
        if (properties == null) {
            throw new IllegalArgumentException("Properties must not be null");
        }
        if (properties.length == 0) {
            throw new IllegalArgumentException("Properties must not be empty");
        }
        return by(Arrays.stream(properties).map((it) -> new CustomOrder(direction, it)).collect(Collectors.toList()));
    }

    public static CustomSort unsorted() {
        return UNSORTED;
    }

    public CustomSort descending() {
        return this.withDirection(CustomDirection.DESC);
    }

    public CustomSort ascending() {
        return this.withDirection(CustomDirection.ASC);
    }

    public boolean isSorted() {
        return !this.isEmpty();
    }

    public boolean isEmpty() {
        return this.orders.isEmpty();
    }

    public boolean isUnsorted() {
        return !this.isSorted();
    }

    public CustomSort reverse() {
        List<CustomOrder> reversed = this.doReverse();
        return by(reversed);
    }

    protected List<CustomOrder> doReverse() {
        List<CustomOrder> reversed = new ArrayList<>();
        Iterator<CustomOrder> var2 = this.iterator();

        while(var2.hasNext()) {
            CustomOrder order = var2.next();
            reversed.add(order.reverse());
        }

        return reversed;
    }

    public List<CustomOrder> getOrders() {
        return this.orders;
    }

    public CustomOrder getOrderFor(String property) {
        Iterator<CustomOrder> var2 = this.iterator();

        CustomOrder order;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            order = (CustomOrder)var2.next();
        } while(!order.getProperty().equals(property));

        return order;
    }

    public Iterator<CustomOrder> iterator() {
        return this.orders.iterator();
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + this.orders.hashCode();
        return result;
    }

    private CustomSort withDirection(CustomDirection direction) {
        List<CustomOrder> result = new ArrayList<>();
        Iterator<CustomOrder> var3 = this.iterator();

        while(var3.hasNext()) {
            CustomOrder order = var3.next();
            result.add(order.with(direction));
        }

        return by(result);
    }

    static {
        DEFAULT_DIRECTION = CustomDirection.ASC;
    }

    public static enum CustomDirection {
        ASC,
        DESC;

        private CustomDirection() {
        }

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static CustomDirection fromString(String value) {
            try {
                return valueOf(value.toUpperCase(Locale.US));
            } catch (Exception var2) {
                Exception e = var2;
                throw new IllegalArgumentException(String.format("Invalid value '%s' for orders given; Has to be either 'desc' or 'asc' (case insensitive)", value), e);
            }
        }

        public static Optional<CustomDirection> fromOptionalString(String value) {
            if (value == null || value.isEmpty()) {
                return Optional.empty();
            } else {
                try {
                    return Optional.of(fromString(value));
                } catch (IllegalArgumentException var2) {
                    return Optional.empty();
                }
            }
        }
    }

    public static class CustomOrder {
        private static final boolean DEFAULT_IGNORE_CASE = false;
        private static final NullHandling DEFAULT_NULL_HANDLING;
        private final CustomDirection direction;
        private final String property;
        private final boolean ignoreCase;
        private final NullHandling nullHandling;

        public CustomOrder(CustomDirection direction, String property) {
            this(direction, property, false, DEFAULT_NULL_HANDLING);
        }

        public CustomOrder(CustomDirection direction, String property, NullHandling nullHandlingHint) {
            this(direction, property, false, nullHandlingHint);
        }

        public CustomOrder(CustomDirection direction, String property, boolean ignoreCase, NullHandling nullHandling) {
            if (property == null || property.isEmpty()) {
                throw new IllegalArgumentException("Property must not be null or empty");
            } else {
                this.direction = direction == null ? CustomSort.DEFAULT_DIRECTION : direction;
                this.property = property;
                this.ignoreCase = ignoreCase;
                this.nullHandling = nullHandling;
            }
        }

        public static CustomOrder by(String property) {
            return new CustomOrder(CustomSort.DEFAULT_DIRECTION, property);
        }

        public static CustomOrder asc(String property) {
            return new CustomOrder(CustomDirection.ASC, property, DEFAULT_NULL_HANDLING);
        }

        public static CustomOrder desc(String property) {
            return new CustomOrder(CustomDirection.DESC, property, DEFAULT_NULL_HANDLING);
        }

        public CustomDirection getDirection() {
            return this.direction;
        }

        public String getProperty() {
            return this.property;
        }

        public boolean isAscending() {
            return this.direction.isAscending();
        }

        public boolean isDescending() {
            return this.direction.isDescending();
        }

        public boolean isIgnoreCase() {
            return this.ignoreCase;
        }

        public CustomOrder with(CustomDirection direction) {
            return new CustomOrder(direction, this.property, this.ignoreCase, this.nullHandling);
        }

        public CustomOrder reverse() {
            return this.with(this.direction == CustomDirection.ASC ? CustomDirection.DESC : CustomDirection.ASC);
        }

        public CustomOrder withProperty(String property) {
            return new CustomOrder(this.direction, property, this.ignoreCase, this.nullHandling);
        }

        public CustomSort withProperties(String... properties) {
            return CustomSort.by(this.direction, properties);
        }

        public CustomOrder ignoreCase() {
            return new CustomOrder(this.direction, this.property, true, this.nullHandling);
        }

        public CustomOrder with(NullHandling nullHandling) {
            return new CustomOrder(this.direction, this.property, this.ignoreCase, nullHandling);
        }

        public CustomOrder nullsFirst() {
            return this.with(NullHandling.NULLS_FIRST);
        }

        public CustomOrder nullsLast() {
            return this.with(NullHandling.NULLS_LAST);
        }

        public CustomOrder nullsNative() {
            return this.with(NullHandling.NATIVE);
        }

        public NullHandling getNullHandling() {
            return this.nullHandling;
        }

        public int hashCode() {
            int result = 17;
            result = 31 * result + this.direction.hashCode();
            result = 31 * result + this.property.hashCode();
            result = 31 * result + (this.ignoreCase ? 1 : 0);
            result = 31 * result + this.nullHandling.hashCode();
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (!(obj instanceof CustomOrder)) {
                return false;
            } else {
                CustomOrder that = (CustomOrder)obj;
                return this.direction.equals(that.direction) && this.property.equals(that.property) && this.ignoreCase == that.ignoreCase && this.nullHandling.equals(that.nullHandling);
            }
        }

        public String toString() {
            String result = String.format("%s: %s", this.property, this.direction);
            if (!NullHandling.NATIVE.equals(this.nullHandling)) {
                result = result + ", " + this.nullHandling;
            }

            if (this.ignoreCase) {
                result = result + ", ignoring case";
            }

            return result;
        }

        static {
            DEFAULT_NULL_HANDLING = NullHandling.NATIVE;
        }
    }

    public static enum NullHandling {
        NATIVE,
        NULLS_FIRST,
        NULLS_LAST;

        private NullHandling() {
        }
    }
}
