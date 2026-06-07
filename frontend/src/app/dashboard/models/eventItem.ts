export type EventStatus = 'draft' | 'pending' | 'confirmed' | 'cancelled';
export type EventType   = 'HALF_DAY' | 'FULL_DAY' | 'MULTI_DAY';
export type Catering    = 'NONE' | 'BEVERAGES' | 'SNACKS' | 'FULL_MEALS';


export interface EventItem {
  id:                 number;
  name:                string;
  description:         string;
  date:                string;        // ISO: "2025-07-15"
  participants:        number;
  location:            string;
  internationalGuests: boolean;
  eventType:           EventType;
  catering:            Catering;
  specialNotes:        string;
  status:              EventStatus;
}

export const EVENT_TYPE_LABELS: Record<EventType, string> = {
  HALF_DAY:  'Halbtag',
  FULL_DAY:  'Ganztag',
  MULTI_DAY: 'Mehrtägig',
};

export const CATERING_LABELS: Record<Catering, string> = {
  NONE:       'Kein Catering',
  BEVERAGES:  'Getränke',
  SNACKS:     'Snacks',
  FULL_MEALS: 'Vollverpflegung',
};

export const STATUS_LABELS: Record<EventStatus, string> = {
  draft:     'Entwurf',
  pending:   'Ausstehend',
  confirmed: 'Bestätigt',
  cancelled: 'Abgesagt',
};
