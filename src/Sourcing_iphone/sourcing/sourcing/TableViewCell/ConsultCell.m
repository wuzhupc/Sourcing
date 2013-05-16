//
//  ConsultCell.m
//  sourcing
//
//  Created by wuzhu on 13-1-12.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "ConsultCell.h"
#import "UIColor+MGExpanded.h"
#import "ApplicationSet.h"
#import "RelativityLaws.h"
#import "OHASBasicHTMLParser.h"
#import "NSAttributedString+Attributes.h"
#import "StringUtil.h"
#import "ConsultVO.h"
#import "ConsultResultVO.h"
#import "DeclareVO.h"
#import "DeclareResultVO.h"
#import "AuditVO.h"
#import "AuditResultVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface ConsultCell ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation ConsultCell

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/
@synthesize dataVO = dataVO_;
/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitConsultCell
{
    [self setSelectionStyle:UITableViewCellSelectionStyleNone];
    [self.backgroundView setBackgroundColor:CCOLOR_TABLEVIEW_BG];
    [self setDashWidth:2 dashGap:3 dashStroke:2];
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        [self commonInitConsultCell];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitConsultCell];
    }
    return self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods
+(CGFloat)calCellHeight:(BaseVO *)kvo
{
    if(kvo==nil)
        return [ConsultCell calCellHeightNoData];
    else if([kvo isMemberOfClass:[ConsultVO class]])
        return [ConsultCell calCellHeightWithConsult:(ConsultVO *)kvo];
    else if([kvo isMemberOfClass:[AuditVO class]])
        return [ConsultCell calCellHeightWithAudit:(AuditVO *)kvo];
    else if([kvo isMemberOfClass:[DeclareVO class]])
        return [ConsultCell calCellHeightWithDeclare:(DeclareVO *)kvo];
    else
        return [ConsultCell calCellHeightNoData];
}

-(void)setData:(BaseVO *)kvo
{
    dataVO_ = kvo;
    //调整高度
    if(kvo==nil)
        [self adjustCellNoData];
    else if([kvo isMemberOfClass:[ConsultVO class]])
        [self adjustCellWithConsult:(ConsultVO *)kvo];
    else if([kvo isMemberOfClass:[AuditVO class]])
        [self adjustCellWithAudit:(AuditVO *)kvo];
    else if([kvo isMemberOfClass:[DeclareVO class]])
        [self adjustCellWithDeclare:(DeclareVO *)kvo];
    else
        [self adjustCellNoData];
             
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

+(CGFloat)calCellHeightNoData
{
    return 40.0f;
}

+(CGFloat)calCellHeightWithConsult:(ConsultVO *)kvo
{
    ConsultResultVO *rvo = kvo.consultResultVO;
    if(rvo==nil)
    {
       return [ConsultCell calCellHeight:kvo.consultcontent resulttitle:nil];
    }else
    {
        return [ConsultCell calCellHeight:kvo.consultcontent resulttitle:rvo.consultresultcontent];
    }
}

+(CGFloat)calCellHeightWithDeclare:(DeclareVO *)kvo
{
    DeclareResultVO *rvo = kvo.declareResultVO;
    if(rvo==nil)
    {
        return [ConsultCell calCellHeight:kvo.declarecontent resulttitle:nil];
    }else
    {
        return [ConsultCell calCellHeight:kvo.declarecontent resulttitle:rvo.declareresultcontent];
    }
}

+(CGFloat)calCellHeightWithAudit:(AuditVO *)kvo
{
    AuditResultVO *rvo = kvo.auditResultVO;
    if(rvo==nil)
    {
        return [ConsultCell calCellHeight:kvo.auditcontent resulttitle:nil];
    }else
    {
        return [ConsultCell calCellHeight:kvo.auditcontent resulttitle:rvo.auditresultcontent];
    }
}

+(CGFloat)calCellHeight:(NSString *)ktitle resulttitle:(NSString *)kresulttitle
{
    OHAttributedLabel *label = [[OHAttributedLabel alloc] initWithFrame:CGRectMake(0,0,304.0f,26.0f)];
    label.numberOfLines = 0;
    label.lineBreakMode = UILineBreakModeCharacterWrap;
    [label setFont:[UIFont boldSystemFontOfSize:16.0f]];
    label.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    label.automaticallyAddLinksForType = NSTextCheckingAllTypes;
    [label setText:ktitle];
    label.attributedText = [OHASBasicHTMLParser attributedStringByProcessingMarkupInAttributedString:label.attributedText];
    [RelativityLaws attributedLabelFitHeight:label];
    CGFloat result = label.frame.size.height;
    if(result<24.0f)
        result = 24.0f;
    if(![StringUtil isEmpty:kresulttitle])
    {
        CGRect frame = label.frame;
        frame.size.width = 288;
        label.frame = frame;
        [label setText:kresulttitle];
        label.attributedText = [OHASBasicHTMLParser attributedStringByProcessingMarkupInAttributedString:label.attributedText];
        [RelativityLaws attributedLabelFitHeight:label];
        
        if(label.frame.size.height<24.0f)
            result += 60.0f;
        else
            result += (36.0f+label.frame.size.height);
    }
    
    return result + 36.0f;
}

       
-(void)adjustCellNoData
{
    self.labelTitle.text = NSLocalizedString(@"没有数据", @"");
    [self.labelStatus setHidden:YES];
    [self.labelPublishtime setHidden:YES];
    [self.radiusViewResult setHidden:YES];
}

-(void)adjustCellWithConsult:(ConsultVO *)kvo
{
    ConsultResultVO *rvo = kvo.consultResultVO;
    if(rvo==nil)
    {
        [self adjustCell:kvo.consultcontent status:nil publishtime:kvo.publishtime resulttitle:nil resultpublishinfo:nil];
    }else
    {
        [self adjustCell:kvo.consultcontent status:nil publishtime:kvo.publishtime resulttitle:rvo.consultresultcontent  resultpublishinfo:[NSString stringWithFormat:@"%@  %@",rvo.publisher,rvo.publishtime]];
    }
}
-(void)adjustCellWithDeclare:(DeclareVO *)kvo
{
    DeclareResultVO *rvo = kvo.declareResultVO;
    if(rvo==nil)
    {
        [self adjustCell:kvo.declarecontent status:nil publishtime:kvo.publishtime resulttitle:nil resultpublishinfo:nil];
    }else
    {
        [self adjustCell:kvo.declarecontent status:[kvo getDeclareStatus] publishtime:kvo.publishtime resulttitle:rvo.declareresultcontent  resultpublishinfo:[NSString stringWithFormat:@"%@  %@",rvo.publisher,rvo.publishtime]];
    }
}
-(void)adjustCellWithAudit:(AuditVO *)kvo
{
    AuditResultVO *rvo = kvo.auditResultVO;
    if(rvo==nil)
    {
        [self adjustCell:kvo.auditcontent status:nil publishtime:kvo.publishtime resulttitle:nil resultpublishinfo:nil];
    }else
    {
        [self adjustCell:kvo.auditcontent status:[kvo getAuditStatus] publishtime:kvo.publishtime resulttitle:rvo.auditresultcontent  resultpublishinfo:[NSString stringWithFormat:@"%@  %@",rvo.publisher,rvo.publishtime]];
    }
}
-(void)adjustCell:(NSString *)ktitle status:(NSString *)kstatus publishtime:(NSString *)kpublishtime resulttitle:(NSString *)kresulttitle resultpublishinfo:(NSString *)kresultpublishinfo
{
    [self.labelTitle setText:ktitle];
    self.labelTitle.attributedText = [OHASBasicHTMLParser attributedStringByProcessingMarkupInAttributedString:self.labelTitle.attributedText];
    if([StringUtil isEmpty:kstatus])
    {
        [self.labelStatus setHidden:YES];
    }
    else
    {
        [self.labelStatus setHidden:NO];
        self.labelStatus.text = kstatus;
    }
    [self.labelPublishtime setHidden:NO];
    self.labelPublishtime.text = kpublishtime;
    [RelativityLaws attributedLabelFitHeight:self.labelTitle];
    [RelativityLaws alignView:self.labelStatus below:self.labelTitle withMargin:4];
    [RelativityLaws alignView:self.labelPublishtime below:self.labelTitle withMargin:4];
    if([StringUtil isEmpty:kresulttitle])
    {
        [self.radiusViewResult setHidden:YES];
    }else
    {
        [self.radiusViewResult setHidden:NO];
        [self.radiusViewResult setCellCount:1];
        [self.labelResultTitle setText:kresulttitle];
        self.labelResultTitle.attributedText = [OHASBasicHTMLParser attributedStringByProcessingMarkupInAttributedString:self.labelResultTitle.attributedText];
        self.labelResultPublishInfo.text = kresultpublishinfo;
        [RelativityLaws attributedLabelFitHeight:self.labelResultTitle];
        [RelativityLaws alignView:self.labelResultPublishInfo below:self.labelResultTitle withMargin:4];
        CGRect frame = self.radiusViewResult.frame;
        frame.size.height = self.labelResultPublishInfo.frame.size.height+self.labelResultPublishInfo.frame.origin.y+4;
        frame.origin.y = self.labelPublishtime.frame.size.height+self.labelPublishtime.frame.origin.y+4;
        self.radiusViewResult.frame = frame;
    }
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
