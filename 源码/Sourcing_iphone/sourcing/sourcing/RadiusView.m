//
//  RadiusView.m
//  sourcing
//
//  Created by wuzhu on 13-1-15.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "RadiusView.h"
#import <QuartzCore/QuartzCore.h>

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface RadiusView ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation RadiusView

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/
@synthesize cellCount = cellCount_;
@synthesize cellHeight = cellHeight_;
@synthesize cellSeparatorColor = cellSeparatorColor_;
/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitRadiusView
{
    cellHeight_ = 44;
    cellCount_ = 2;
	self.layer.borderWidth = CINT_DEFAULT_BORDERWIDTH;
    [self setCornerRadius:10.0f];
    [self setBorderColor:CCOLOR_DEFAULT_BORDERCOLOR];
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        [self commonInitRadiusView];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitRadiusView];
    }
    return self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

-(void)drawRect:(CGRect)rect
{
   if(self.cellCount<2||self.cellHeight == 0)
       return;
    //画间隔线
    if(!self.cellSeparatorColor)
    {
        [self setCellSeparatorColor:CCOLOR_DEFAULT_BORDERCOLOR];
    }
    CGContextRef c = UIGraphicsGetCurrentContext();
    CGContextSetStrokeColorWithColor(c, [self.cellSeparatorColor CGColor]);
    CGContextSetLineWidth(c,CINT_DEFAULT_BORDERWIDTH);
    CGContextBeginPath(c);
    for (int i =0; i<self.cellCount-1; i++) {
        CGContextMoveToPoint(c, 0.0f,self.cellHeight*(i+1));
        CGContextAddLineToPoint(c, rect.size.width, self.cellHeight*(i+1));
    }
    CGContextStrokePath(c);
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods
-(void)setCornerRadius:(CGFloat)kcornerRadius
{
    self.layer.cornerRadius = kcornerRadius;
}

-(void)setBorderColor:(UIColor
                       *)kcolor
{
    self.layer.borderColor = [kcolor CGColor];
}

-(void)setCellCount:(NSUInteger)kcount
{
    if(self.cellCount == kcount)
        return;
    cellCount_ = kcount;
    if(self.cellCount>1)
    {
    CGRect frame = self.frame;
    frame.size.height = kcount * self.cellHeight;
    self.frame = frame;
    }
}
-(void)setCellHeight:(NSUInteger)kheight
{
    if(self.cellHeight = kheight)
        return;
    cellHeight_ = kheight;
    CGRect frame = self.frame;
    frame.size.height = kheight * self.cellCount;
    self.frame = frame;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
