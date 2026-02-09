declare module 'react-vertical-timeline-component' {
  import * as React from 'react'

  export interface VerticalTimelineProps extends React.HTMLAttributes<HTMLDivElement> {}
  export const VerticalTimeline: React.FC<VerticalTimelineProps>

  export interface VerticalTimelineElementProps extends React.HTMLAttributes<HTMLDivElement> {
    className?: string
    contentStyle?: React.CSSProperties
    contentArrowStyle?: React.CSSProperties
    date?: React.ReactNode
    iconStyle?: React.CSSProperties
    icon?: React.ReactNode
  }
  export const VerticalTimelineElement: React.FC<VerticalTimelineElementProps>

  export default {
    VerticalTimeline,
    VerticalTimelineElement,
  }
}
