//
//  AuditVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "AuditVO.h"
#import "ConsultCell.h"

@interface AuditVO()
{
@private
    NSInteger auditid;
}
@end

@implementation AuditVO

@synthesize auditcontent = auditcontent_;
@synthesize auditResultVO = auditResultVO_;
@synthesize publishtime = publishtime_;

-(void)setAuditid:(NSInteger)kauditid
{
    auditid = kauditid;
    self.Id = kauditid;
}
-(NSInteger)Auditid
{
    return auditid;
}

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return [ConsultCell calCellHeight:self];
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    static NSString *cellIdentifier = @"ConsultCell";
    ConsultCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:cellIdentifier owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    [cell setData:self];
    return cell;
}
-(NSString *)getAuditStatus
{
    if(self.auditResultVO == nil)
        return @"等待审核中";
    return self.auditResultVO.getAuditStatus;
}
@end
